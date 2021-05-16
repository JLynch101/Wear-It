package ie.wit.wearit.main.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import ie.wit.wearit.R
import ie.wit.wearit.main.AboutUs
import ie.wit.wearit.main.LoginActivity
import ie.wit.wearit.main.MainApp
import ie.wit.wearit.main.fragments.AboutUsFragment
import ie.wit.wearit.main.fragments.AddFragment
import ie.wit.wearit.main.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        app = application as MainApp
        app.auth = FirebaseAuth.getInstance()
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val AboutUsFragment = AboutUsFragment()
        navView.getHeaderView(0).nav_header_email.text = app.auth.currentUser?.email


        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miItem1 -> makeCurrentFragment(homeFragment)
                R.id.miItem2 ->  makeCurrentFragment(addFragment)
                R.id.miItem3 ->  signOut()
                R.id.miItem4 ->  makeCurrentFragment(AboutUsFragment)
            }
            true
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        makeCurrentFragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home_icon -> makeCurrentFragment(homeFragment)
                R.id.add_icon -> makeCurrentFragment(addFragment)
            }
            true
        }
    }
    private fun signOut()
    {
        app.auth.signOut()
        startActivity<LoginActivity>()
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrap, fragment)
            commit()
        }

    }



