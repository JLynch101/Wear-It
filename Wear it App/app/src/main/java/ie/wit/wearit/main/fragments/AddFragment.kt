package ie.wit.wearit.main.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_clothes.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.clothes.R
import org.wit.clothes.helpers.readImage
import org.wit.clothes.helpers.readImageFromPath
import org.wit.clothes.helpers.showImagePicker
import org.wit.clothes.main.MainApp
import org.wit.clothes.models.Location
import org.wit.clothes.models.ClothesModel

class ClothesActivity : AppCompatActivity(), AnkoLogger {

    var clothes = ClothesModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var edit = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)
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
            startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
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

