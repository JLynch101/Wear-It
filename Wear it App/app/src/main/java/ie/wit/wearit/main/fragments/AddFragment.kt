package ie.wit.wearit.main.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ie.wit.wearit.R
import ie.wit.wearit.main.ClothesActivity

class AddFragment: Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intent = Intent (this.context, ClothesActivity::class.java)
        startActivity(intent)
    }
}