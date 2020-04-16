package by.valery.mobile.data.repository

import androidx.lifecycle.LiveData
import by.valery.mobile.data.api.CartApi
import by.valery.mobile.data.database.ProductDao
import by.valery.mobile.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ProductRepository(private val productDao: ProductDao, private val cartApi: CartApi) {
	
	fun getProducts(): LiveData<List<Product>> = productDao.all()
	
	fun getProductById(productId: String): LiveData<Product> {
		return productDao.getProductById(productId)
	}
	
	suspend fun refreshProductById(productId: String): Unit = withContext(Dispatchers.IO) {
		try {
			val product = cartApi.getProductByIdAsync(productId).await()
			productDao.insertAll(product)
		} catch (e: Exception) {
			Timber.e(e)
		}
	}
	
	suspend fun refreshProducts(): Unit = withContext(Dispatchers.IO) {
		try {
			val products = cartApi.getCartAsync().await().products
			productDao.insertAll(*products.toTypedArray())
		} catch (e: Exception) {
			Timber.e(e)
		}
	}
}
