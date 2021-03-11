package ie.wit.wearit.main.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import ie.wit.wearit.R
import ie.wit.wearit.main.MainApp
import ie.wit.wearit.main.MapActivity
import ie.wit.wearit.main.helpers.readImage
import ie.wit.wearit.main.helpers.readImageFromPath
import ie.wit.wearit.main.helpers.showImagePicker
import ie.wit.wearit.main.models.ClothesModel
import ie.wit.wearit.main.models.Location
import kotlinx.android.synthetic.main.fragment_add.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    class ClothesActivity : AppCompatActivity(), AnkoLogger {

        var clothes = ClothesModel()
        lateinit var app: MainApp
        val IMAGE_REQUEST = 1
        val LOCATION_REQUEST = 2
        var edit = false;


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_add)
            toolbarAdd.title = title
            setSupportActionBar(toolbarAdd)
            info("Clothes Activity started..")

            app = application as MainApp

            if (intent.hasExtra("clothes_edit")) {
                edit = true
                clothes = intent.extras?.getParcelable<ClothesModel>("clothes_edit")!!
                clothesTitle.setText(clothes.title)
                description.setText(clothes.description)
                clothesImage.setImageBitmap(readImageFromPath(this, clothes.image))
                if (clothes.image != null) {
                    chooseImage.setText(R.string.change_clothes_image)
                }
                btnAdd.setText(R.string.save_clothes)
            }

            btnAdd.setOnClickListener() {
                clothes.title = clothesTitle.text.toString()
                clothes.description = description.text.toString()
                if (clothes.title.isEmpty()) {
                    toast(R.string.enter_clothes_title)
                } else {
                    if (edit) {
                        app.clothess.update(clothes.copy())
                    } else {

                        app.clothess.create(clothes.copy())
                    }
                }
                info("add Button Pressed: $clothesTitle")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }

            chooseImage.setOnClickListener {
                showImagePicker(this, IMAGE_REQUEST)
            }

            clothesLocation.setOnClickListener {
                val location = Location(52.245696, -7.139102, 15f)
                if (clothes.zoom != 0f) {
                    location.lat = clothes.lat
                    location.lng = clothes.lng
                    location.zoom = clothes.zoom
                }
                startActivityForResult(
                    intentFor<MapActivity>().putExtra("location", location),
                    LOCATION_REQUEST
                )
            }
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_clothes, menu)
            if (edit && menu != null) menu.getItem(0).setVisible(true)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item_delete -> {
                    app.clothess.delete(clothes)
                    finish()
                }
                R.id.item_cancel -> {
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                IMAGE_REQUEST -> {
                    if (data != null) {
                        clothes.image = data.getData().toString()
                        clothesImage.setImageBitmap(readImage(this, resultCode, data))
                        chooseImage.setText(R.string.change_clothes_image)
                    }
                }
                LOCATION_REQUEST -> {
                    if (data != null) {
                        val location = data.extras?.getParcelable<Location>("location")!!
                        clothes.lat = location.lat
                        clothes.lng = location.lng
                        clothes.zoom = location.zoom
                    }
                }
            }
        }
    }
    fun newInstance(param1: String, param2: String) =
        AddFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
}

