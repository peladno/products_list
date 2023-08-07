package com.example.androidapicall.Model.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidapicall.Model.Local.Dao.ProductsDao
import com.example.androidapicall.Model.Local.Entities.ProductDetailEntity
import com.example.androidapicall.Model.Local.Entities.ProductListEntity
import com.example.androidapicall.Model.Mapper.apiProductDetailEntity
import com.example.androidapicall.Model.Mapper.apiProductEntity
import com.example.androidapicall.Model.Remote.RetrofitClient

class ProductsRepository(private val productsDao: ProductsDao) {

    private val networkService = RetrofitClient.retrofitInstance()
    val productsListLiveData = productsDao.getAllProducts()


    suspend fun fetchProducts() {
        try {
            val response = networkService.getProductsList()
            if (response.isSuccessful) {
                val productList = response.body()
                productList?.let {
                    Log.d("products", it.toString())
                    productsDao.insertAllProducts(apiProductEntity(it))
                }
            } else {
                Log.d("Error fetching", "${response.code()}-${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("Error", "Error fetching course: ${e.message}", e)
        }
    }

    suspend fun fetchProductDetail(id: Int): ProductDetailEntity? {
        try {
            val response = networkService.getProductsDetail(id)
            if (response.isSuccessful) {
                val productDetail = response.body()
                productDetail?.let {
                    val prod = apiProductDetailEntity(it)
                    productsDao.insertProductsDetail(prod)
                    return prod
                }
            } else {
                Log.d("Repo", "${response.code()}-${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("Error", "Error fetching product detail: ${e.message}", e)
        }
        return null
    }

}
