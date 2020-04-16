package by.valery.mobile.ui.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.valery.mobile.R
import by.valery.mobile.data.model.Product
import com.bumptech.glide.Glide

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
	
	var productList: List<Product> = listOf()
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
		ProductViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.product_item,
				parent,
				false
			)
		)
	
	override fun getItemCount(): Int = productList.size
	
	override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
		val product = productList[position]
		holder.bind(product)
		holder.itemView.setOnClickListener { view ->
			val action = ProductListFragmentDirections.toProductFragment(product.productId)
			view.findNavController().navigate(action)
		}
	}
	
	inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		
		private val image: ImageView = view.findViewById(R.id.itemProductImage)
		private val productDescription: TextView = view.findViewById(R.id.productDescription)
		private val productPrice: TextView = view.findViewById(R.id.productPrice)
		
		fun bind(product: Product) {
			productDescription.text = product.name
			productPrice.text = productPrice.context.getString(R.string.price_text, product.price)
			
			Glide.with(image.context.applicationContext)
				.load(product.imageUrl)
				.into(image)
		}
	}
}
