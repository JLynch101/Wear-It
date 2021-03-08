package ie.wit.wearit.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.wearit.Firebase.FirebaseRepo
import ie.wit.wearit.R
import ie.wit.wearit.products.ProductListAdapter
import ie.wit.wearit.products.ProductModel
import kotlinx.android.synthetic.main.fragment_home.*


    const val TAG: String = "HOMEPAGE_LOG"
    class HomeActivity : AppCompatActivity() {

    private val firebaseRepo: FirebaseRepo =
        FirebaseRepo()

    private var productList: List<ProductModel> = ArrayList()
    private val productListAdapter: ProductListAdapter = ProductListAdapter(productList)

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        product_list.layoutManager  = LinearLayoutManager(this)
        product_list.adapter = productListAdapter

        if(firebaseRepo.getUser() == null){
            // create new user
            firebaseRepo.createUser().addOnCompleteListener{
                if (it.isSuccessful){
                    loadProductData()
                }else{
                    Log.d(TAG, "Error:${it.exception!!.message}")
                }
            }
        } else {
            //user logged in
            loadProductData()
        }
        product_list.layoutManager  = LinearLayoutManager(this)
        product_list.adapter = productListAdapter

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun loadProductData() {
    firebaseRepo.getProductList().addOnCompleteListener{
        if(it.isSuccessful){
            productList = it.result!!.toObjects(ProductModel::class.java)
            productListAdapter.productListItems = productList
            productListAdapter.notifyDataSetChanged()
        }else{
            Log.d(TAG, "Error: ${it.exception!!.message}")
        }
    }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}