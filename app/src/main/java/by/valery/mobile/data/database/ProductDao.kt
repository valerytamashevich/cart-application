package by.valery.mobile.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.valery.mobile.data.model.Product

@Dao
interface ProductDao {
	
	@Query("SELECT * FROM product")
	fun all(): LiveData<List<Product>>
	
	@Query("SELECT * FROM product WHERE productId = :productId")
	fun getProductById(productId: String): LiveData<Product>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertAll(vararg product: Product)
}
