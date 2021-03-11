package ie.wit.wearit.main.activites

import android.app.Application
import ie.wit.wearit.main.models.ClothesJSONStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

import org.wit.clothes.models.ClothesStore

class MainApp : Application(), AnkoLogger {

    lateinit var clothess: ClothesStore

    override fun onCreate() {
        super.onCreate()
        clothess = ClothesJSONStore(applicationContext)
        info("Clothes started")
    }
}

