package ie.wit.wearit.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.wearit.R
import kotlinx.android.synthetic.main.items_with_image.view.*

private const val PRODUCT_TYPE_DESC: Int = 0
private const val PRODUCT_TYPE_IMAGE: Int = 1

class ProductListAdapter(var productListItems: List<ProductModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (productModel: ProductModel){
    itemView.product_name.text = productModel.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_with_image,parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productListItems.size

    }

    override fun getItemViewType(position: Int): Int {
        return if (productListItems[position].product_type == 0L) {
            PRODUCT_TYPE_DESC
        }else{
            PRODUCT_TYPE_IMAGE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder).bind(productListItems[position])
    }
    }


