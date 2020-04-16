package by.valery.mobile.ui.productlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import by.valery.mobile.R
import by.valery.mobile.util.ProductDiffUtilCallback
import kotlinx.android.synthetic.main.product_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment(R.layout.product_list_fragment) {
	
	private val viewModel: ProductListViewModel by viewModel()
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		val adapter = ProductAdapter()
		viewModel.products.observe(viewLifecycleOwner, Observer { newList ->
			val oldList = adapter.productList
			val callback =
				ProductDiffUtilCallback(oldList, newList)
			
			val productDiffResult = DiffUtil.calculateDiff(callback, false)
			adapter.productList = newList
			productDiffResult.dispatchUpdatesTo(adapter)
		})
		productList.apply {
			this.layoutManager = GridLayoutManager(context, 2)
			this.adapter = adapter
			this.setHasFixedSize(true)
		}
		
		viewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
			productListProgressBar.visibility = it
		})
	}
	
	override fun onResume() {
		super.onResume()
		
		viewModel.loadProducts()
	}
}
