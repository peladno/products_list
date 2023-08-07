package com.example.androidapicall.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat
import java.util.Locale


@Entity(tableName = "product_list_table")
data class ProductListEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String

)