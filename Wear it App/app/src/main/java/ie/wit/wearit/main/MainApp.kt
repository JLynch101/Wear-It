package ie.wit.wearit.main

import android.app.Application
import com.google.firebase.database.DatabaseReference
import ie.wit.wearit.main.models.ClothesJSONStore
import ie.wit.wearit.main.models.ClothesStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {

    lateinit var clothess: ClothesStore
    lateinit var database: DatabaseReference

    override fun onCreate() {
        super.onCreate()
        clothess = ClothesJSONStore(applicationContext)
        info("Clothes started")
    }
}

