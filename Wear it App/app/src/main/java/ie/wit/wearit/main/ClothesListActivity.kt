package ie.wit.wearit.main


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.wit.wearit.R
import ie.wit.wearit.main.*
import ie.wit.wearit.main.models.ClothesModel
import kotlinx.android.synthetic.main.activity_clothes_list.*
import kotlinx.android.synthetic.main.card_clothes.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult


class ClothesListActivity : AppCompatActivity(), ClothesListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes_list)
        app = application as MainApp
        toolbar.title = title
        setSupportActionBar(toolbar)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ClothesAdapter(app.clothess.findAll(), this)
        loadClothess()
    }

    private fun loadClothess() {
        showClothess( app.clothess.findAll())
    }

    fun showClothess (clothess: List<ClothesModel>) {
        recyclerView.adapter = ClothesAdapter(clothess, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<ClothesActivity>(200)
            R.id.item_map -> startActivity<ClothesMapsActivity>()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClothesClick(clothes: ClothesModel) {
        startActivityForResult(intentFor<ClothesActivity>().putExtra("clothes_edit", clothes), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadClothess()
        super.onActivityResult(requestCode, resultCode, data)
    }
}

