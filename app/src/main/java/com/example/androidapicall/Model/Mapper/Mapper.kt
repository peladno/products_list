package com.example.androidapicall.Model.Mapper

import com.example.androidapicall.Model.Local.Entities.ProductDetailEntity
import com.example.androidapicall.Model.Local.Entities.ProductListEntity
import com.example.androidapicall.Model.Remote.ApiModel.ProductDetail
import com.example.androidapicall.Model.Remote.ApiModel.ProductList

fun apiProductEntity(productList: List<ProductList>): List<ProductListEntity> {
    return productList.map {
        ProductListEntity(
            id = it.id, name = it.name, price = it.price, image = it.image
        )
    }
}

fun apiProductDetailEntity(productDetail: ProductDetail): ProductDetailEntity {
    return ProductDetailEntity(
        id = productDetail.id,
        name = productDetail.name,
        price = productDetail.price,
        image = productDetail.image,
        description = productDetail.description,
        lastPrice = productDetail.lastPrice,
        credit = productDetail.credit
    )
}

