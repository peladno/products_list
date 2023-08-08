package com.example.androidapicall.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidapicall.Model.Local.Database.ProductsDatabase
import com.example.androidapicall.Model.Local.Entities.ProductDetailEntity
import com.example.androidapicall.Model.Local.Entities.ProductListEntity
import com.example.androidapicall.Model.Repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductsRepository
    private val productDetailLiveData = MutableLiveData<ProductDetailEntity?>()
    private val productListLiveData: LiveData<List<ProductListEntity>>

    init {
        val db = ProductsDatabase.getDatabase(application)
        val productDao = db.getProductsDao()

        repository = ProductsRepository(productDao)
        viewModelScope.launch { repository.fetchProducts() }

        productListLiveData = repository.productsListLiveData
    }

    fun getProductList(): LiveData<List<ProductListEntity>> = productListLiveData
    fun getProductDetail(): MutableLiveData<ProductDetailEntity?> = productDetailLiveData

    fun fetchProductDetail(id: Int) {
        viewModelScope.launch {
            val productDetail = repository.fetchProductDetail(id)
            productDetail?.let {
                if (it != productDetailLiveData.value) {
                    productDetailLiveData.value = it
                }
            }
        }
    }

    fun clearData() {
        productDetailLiveData.value = null
    }
}
