package ie.wit.wearit.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.clothes.models.ClothesJSONStore
import org.wit.clothes.models.ClothesMemStore
import org.wit.clothes.models.ClothesStore

class MainApp : Application(), AnkoLogger {

    lateinit var clothess: ClothesStore

    override fun onCreate() {
        super.onCreate()
        clothess = ClothesJSONStore(applicationContext)
        info("Clothes started")
    }
}

