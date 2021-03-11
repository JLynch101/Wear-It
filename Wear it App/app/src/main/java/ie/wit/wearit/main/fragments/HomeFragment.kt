package ie.wit.wearit.main.fragments


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.wearit.R
import ie.wit.wearit.main.ClothesAdapter
import ie.wit.wearit.main.ClothesListener
import ie.wit.wearit.main.ClothesMapsActivity
import ie.wit.wearit.main.MainApp
import ie.wit.wearit.main.models.ClothesModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    class ClothesListActivity : AppCompatActivity(), ClothesListener {

        lateinit var app: MainApp

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_home)
            app = application as MainApp
            toolbar.title = title
            setSupportActionBar(toolbar)

            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = ClothesAdapter(app.clothess.findAll(), this)
            loadClothess()
        }

        private fun loadClothess() {
            showClothess(app.clothess.findAll())
        }

        fun showClothess(clothess: List<ClothesModel>) {
            recyclerView.adapter = ClothesAdapter(clothess, this)
            recyclerView.adapter?.notifyDataSetChanged()
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item_add -> startActivityForResult<AddFragment.ClothesActivity>(200)
                R.id.item_map -> startActivity<ClothesMapsActivity>()
            }
            return super.onOptionsItemSelected(item)
        }

        override fun onClothesClick(clothes: ClothesModel) {
            startActivityForResult(
                intentFor<AddFragment.ClothesActivity>().putExtra(
                    "clothes_edit",
                    clothes
                ), 0
            )
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            loadClothess()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun newInstance(param1: String, param2: String) =
        HomeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
}

