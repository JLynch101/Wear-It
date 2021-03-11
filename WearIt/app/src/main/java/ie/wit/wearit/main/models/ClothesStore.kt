package org.wit.clothes.models

interface ClothesStore {
    fun findAll(): List<ClothesModel>
    fun create(clothes: ClothesModel)
    fun update(clothes: ClothesModel)
    fun delete(clothes: ClothesModel)
}

