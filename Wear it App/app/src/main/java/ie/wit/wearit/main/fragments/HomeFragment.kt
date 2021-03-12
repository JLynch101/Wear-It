package ie.wit.wearit.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.Button
import ie.wit.wearit.R
import ie.wit.wearit.main.ClothesActivity
import kotlinx.android.synthetic.main.fragment_add.*


class HomeFragment: Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intent = Intent (this.context, ClothesActivity::class.java)
        startActivity(intent)
    }
}