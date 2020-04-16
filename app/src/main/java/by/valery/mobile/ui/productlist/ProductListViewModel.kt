package by.valery.mobile.ui.productlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.valery.mobile.base.AsyncViewModel
import by.valery.mobile.data.model.Product
import by.valery.mobile.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(private val productRepository: ProductRepository) : AsyncViewModel() {
	
	val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
	
	lateinit var products: LiveData<List<Product>>
	
	init {
		loadingVisibility.value = View.GONE
		loadProducts()
	}
	
	fun loadProducts() {
		loadingVisibility.value = View.VISIBLE
		
		products = productRepository.getProducts()
		
		launch { productRepository.refreshProducts() }
		
		loadingVisibility.value = View.GONE
	}
}
