package com.example.androidapicall.Model.Remote

import com.example.androidapicall.Model.Remote.ApiModel.ProductDetail
import com.example.androidapicall.Model.Remote.ApiModel.ProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("products")
    suspend fun getProductsList(): Response<List<ProductList>>


    //chequear tambien si tiene que ser int o string
    @GET("details/{id}")
    suspend fun getProductsDetail(@Path("id") id: Int): Response<ProductDetail>

}