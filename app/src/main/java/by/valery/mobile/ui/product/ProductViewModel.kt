package by.valery.mobile.ui.product

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.valery.mobile.base.AsyncViewModel
import by.valery.mobile.data.model.Product
import by.valery.mobile.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : AsyncViewModel() {
	
	val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
	
	lateinit var product: LiveData<Product>
	
	init {
		loadingVisibility.value = View.GONE
	}
	
	fun loadProduct(productId: String) {
		loadingVisibility.value = View.VISIBLE
		
		product = productRepository.getProductById(productId)
		
		launch { productRepository.refreshProductById(productId) }
		
		loadingVisibility.value = View.GONE
	}
}
