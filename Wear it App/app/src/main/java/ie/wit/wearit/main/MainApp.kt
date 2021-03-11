package ie.wit.wearit.main

import android.app.Application
import ie.wit.wearit.main.models.ClothesJSONStore
import ie.wit.wearit.main.models.ClothesStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {

    lateinit var clothess: ClothesStore

    override fun onCreate() {
        super.onCreate()
        clothess = ClothesJSONStore(applicationContext)
        info("Clothes started")
    }
}

