package ie.wit.wearit.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.wearit.R
import ie.wit.wearit.main.ClothesAdapter
import ie.wit.wearit.main.ClothesListener
import ie.wit.wearit.main.MainApp
import ie.wit.wearit.main.models.ClothesModel

import kotlinx.android.synthetic.main.activity_clothes_list.view.*

class HomeFragment : Fragment(),ClothesListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = activity?.application as MainApp
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
 val root = inflater.inflate(R.layout.fragment_home,container,false)
        root.recyclerView.layoutManager = LinearLayoutManager(activity)
        root.recyclerView.adapter = ClothesAdapter(app.clothess.findAll(), this)

        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onClothesClick(clothes: ClothesModel) {
        TODO("Not yet implemented")
    }
}