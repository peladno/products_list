package com.example.androidapicall.Model.Remote.ApiModel

data class ProductDetail(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)