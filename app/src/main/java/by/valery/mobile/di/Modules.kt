package by.valery.mobile.di

import androidx.room.Room
import by.valery.mobile.BuildConfig
import by.valery.mobile.data.api.CartApi
import by.valery.mobile.data.database.AppDatabase
import by.valery.mobile.data.repository.ProductRepository
import by.valery.mobile.ui.product.ProductViewModel
import by.valery.mobile.ui.productlist.ProductListViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Modules {
	private val networkModule: Module = module {
		single {
			val retrofit: Retrofit = get()
			retrofit.create(CartApi::class.java)
		}
		single {
			val httpClient: OkHttpClient = get()
			Retrofit.Builder()
				.baseUrl(BuildConfig.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(CoroutineCallAdapterFactory())
				.client(httpClient)
				.build()
		}
		single {
			val interceptor = HttpLoggingInterceptor()
			interceptor.level = HttpLoggingInterceptor.Level.BODY
			OkHttpClient.Builder().addInterceptor(interceptor).build()
		}
	}
	
	private val databaseModule: Module = module {
		single {
			val db: AppDatabase = get()
			db.productDao()
		}
		single {
			Room.databaseBuilder(get(), AppDatabase::class.java, "products")
				.fallbackToDestructiveMigration()
				.build()
		}
	}
	
	private val repositoryModule: Module = module {
		single { ProductRepository(get(), get()) }
	}
	
	private val viewModelModule: Module = module {
		viewModel { ProductListViewModel(get()) }
		viewModel { ProductViewModel(get()) }
	}
	
	val allModules: List<Module> = listOf(
		databaseModule,
		networkModule,
		repositoryModule,
		viewModelModule
	)
}
