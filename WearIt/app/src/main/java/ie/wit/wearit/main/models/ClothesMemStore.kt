package org.wit.clothes.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ClothesMemStore : ClothesStore, AnkoLogger {

    val clothess = ArrayList<ClothesModel>()

    override fun findAll(): List<ClothesModel> {
        return clothess
    }

    override fun create(clothes: ClothesModel) {
        clothes.id = getId()
        clothess.add(clothes)
        logAll()
    }

    override fun update(clothes: ClothesModel) {
        var foundClothes: ClothesModel? = clothess.find { p -> p.id == clothes.id }
        if (foundClothes != null) {
            foundClothes.title = clothes.title
            foundClothes.description = clothes.description
            foundClothes.image = clothes.image
            foundClothes.lat = clothes.lat
            foundClothes.lng = clothes.lng
            foundClothes.zoom = clothes.zoom
            logAll();
        }
    }

    override fun delete(clothes: ClothesModel) {
        clothess.remove(clothes)
    }

    fun logAll() {
        clothess.forEach { info("${it}") }
    }
}

