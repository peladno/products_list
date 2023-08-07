package com.example.androidapicall.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product_detail_table")
data class ProductDetailEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean

)
