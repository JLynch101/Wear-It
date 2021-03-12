package ie.wit.wearit.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import ie.wit.wearit.R
import ie.wit.wearit.main.helpers.readImage
import ie.wit.wearit.main.helpers.readImageFromPath
import ie.wit.wearit.main.helpers.showImagePicker
import ie.wit.wearit.main.models.ClothesModel
import kotlinx.android.synthetic.main.activity_clothes.*
import org.jetbrains.anko.*


class ClothesActivity : AppCompatActivity(), AnkoLogger {

    var clothes = ClothesModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var edit = false;
    lateinit var option : Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        val size = arrayOf("X-Small", "Small", "Medium", "Large", "X-Large")


        size_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, size)

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Clothes Activity started..")
        app = application as MainApp
        



        if (intent.hasExtra("clothes_edit")) {
            edit = true
            clothes = intent.extras?.getParcelable<ClothesModel>("clothes_edit")!!
            clothesTitle.setText(clothes.title)
            description.setText(clothes.description)
            price.setText(clothes.price)
            clothesImage.setImageBitmap(readImageFromPath(this, clothes.image))
            if (clothes.image != null) {
                chooseImage.setText(R.string.change_clothes_image)
            }
            btnAdd.setText(R.string.save_clothes)
        }

        btnAdd.setOnClickListener() {
            clothes.title = clothesTitle.text.toString()
            clothes.description = description.text.toString()
            clothes.price = price.text.toString()
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
            setResult(RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
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

        }
    }
}

