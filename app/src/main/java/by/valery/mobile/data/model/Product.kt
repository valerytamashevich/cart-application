package by.valery.mobile.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
	@field:PrimaryKey @SerializedName("product_id") val productId: String,
	val name: String,
	val price: Int,
	@SerializedName("image") val imageUrl: String,
	var description: String?
)
