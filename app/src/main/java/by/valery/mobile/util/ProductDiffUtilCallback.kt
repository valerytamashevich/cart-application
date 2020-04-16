package by.valery.mobile.util

import androidx.recyclerview.widget.DiffUtil
import by.valery.mobile.data.model.Product

class ProductDiffUtilCallback(
	private val oldList: List<Product>,
	private val newList: List<Product>
) : DiffUtil.Callback() {
	
	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
		oldList[oldItemPosition].productId == newList[newItemPosition].productId
	
	override fun getOldListSize(): Int = oldList.size
	
	override fun getNewListSize(): Int = newList.size
	
	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val newItem = newList[newItemPosition]
		val oldItem = oldList[oldItemPosition]
		return newItem == oldItem
	}
}
