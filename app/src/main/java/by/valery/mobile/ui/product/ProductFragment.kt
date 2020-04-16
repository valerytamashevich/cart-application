package by.valery.mobile.ui.product

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.valery.mobile.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : Fragment(R.layout.product_fragment) {
	
	private val viewModel: ProductViewModel by viewModel()
	
	private val args: ProductFragmentArgs by navArgs()
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
			productProgressBar.visibility = it
		})
		
		val productId = args.productId
		
		viewModel.loadProduct(productId)
		
		viewModel.product.observe(viewLifecycleOwner, Observer {
			Glide.with(productImage)
				.load(it.imageUrl)
				.into(productImage)
			productDescription.text = it.description
			productName.text = it.name
			productPrice.text = getString(R.string.price_text, it.price)
		})
		
		productDescription.movementMethod = ScrollingMovementMethod()
	}
}
