package ie.wit.wearit.main.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.clothes.helpers.*
import java.util.*

val JSON_FILE = "clothess.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ClothesModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ClothesJSONStore : ClothesStore, AnkoLogger {

    val context: Context
    var clothess = mutableListOf<ClothesModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ClothesModel> {
        return clothess
    }

    override fun create(clothes: ClothesModel) {
        clothes.id = generateRandomId()
        clothess.add(clothes)
        serialize()
    }

    override fun update(clothes: ClothesModel) {
        val clothessList = findAll() as ArrayList<ClothesModel>
        var foundClothes: ClothesModel? = clothessList.find { p -> p.id == clothes.id }
        if (foundClothes != null) {
            foundClothes.title = clothes.title
            foundClothes.description = clothes.description
            foundClothes.image = clothes.image
            foundClothes.lat = clothes.lat
            foundClothes.lng = clothes.lng
            foundClothes.zoom = clothes.zoom
        }
        serialize()
    }

    override fun delete(clothes: ClothesModel) {
        clothess.remove(clothes)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(clothess, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        clothess = Gson().fromJson(jsonString, listType)
    }
}

