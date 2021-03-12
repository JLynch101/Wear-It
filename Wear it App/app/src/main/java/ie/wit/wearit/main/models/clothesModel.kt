package ie.wit.wearit.main.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClothesModel(var id: Long = 0,
                        var title: String = "",
                        var description: String = "",
                        var price: String = "",
                        var image: String = "",
                        var lat : Double = 0.0,
                        var lng: Double = 0.0,
                        var zoom: Float = 0f) : Parcelable



