package ie.wit.wearit.main.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ie.wit.wearit.R
import ie.wit.wearit.main.fragments.AddFragment

import ie.wit.wearit.main.fragments.CartFragment
import ie.wit.wearit.main.fragments.ExtraFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = ExtraFragment()
        val addFragment = AddFragment()
        val cartFragment = CartFragment()

        makeCurrentFragment(homeFragment)

        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home_icon -> makeCurrentFragment(homeFragment)
                R.id.add_icon -> makeCurrentFragment(addFragment)
                R.id.cart_icon -> makeCurrentFragment(cartFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrap, fragment)
            commit()
        }

    }
