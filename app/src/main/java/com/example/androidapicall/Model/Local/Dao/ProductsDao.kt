package com.example.androidapicall.Model.Local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidapicall.Model.Local.Entities.ProductDetailEntity
import com.example.androidapicall.Model.Local.Entities.ProductListEntity

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(listProducts: List<ProductListEntity>)

    @Query("SELECT * FROM product_list_table ORDER BY id ASC")
    fun getAllProducts(): LiveData<List<ProductListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsDetail(course: ProductDetailEntity)

    @Query("SELECT * FROM product_detail_table WHERE id=:id")
    fun getProductDetailById(id: String): LiveData<ProductDetailEntity>
}