package ie.wit.wearit.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.wearit.R
import ie.wit.wearit.main.helpers.readImageFromPath
import kotlinx.android.synthetic.main.card_clothes.view.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import org.wit.clothes.models.ClothesModel

interface ClothesListener {
    fun onClothesClick(clothes: ClothesModel)
}

class ClothesAdapter constructor(private var clothess: List<ClothesModel>,
                                 private val listener: ClothesListener) : RecyclerView.Adapter<ClothesAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_clothes, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val clothes = clothess[holder.adapterPosition]
        holder.bind(clothes, listener)
    }

    override fun getItemCount(): Int = clothess.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(clothes: ClothesModel, listener: ClothesListener) {
            itemView.clothesTitle.text = clothes.title
            itemView.description.text = clothes.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, clothes.image))
            itemView.setOnClickListener { listener.onClothesClick(clothes) }
        }
    }
}

