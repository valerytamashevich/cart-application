package by.valery.mobile.data.api

import by.valery.mobile.data.model.Cart
import by.valery.mobile.data.model.Product
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {
	
	@GET("cart/list")
	fun getCartAsync(): Deferred<Cart>
	
	@GET("cart/{productId}/detail")
	fun getProductByIdAsync(@Path("productId") productId: String): Deferred<Product>
}
