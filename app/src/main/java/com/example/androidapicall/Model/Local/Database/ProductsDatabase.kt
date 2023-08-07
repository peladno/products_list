package com.example.androidapicall.Model.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidapicall.Model.Local.Dao.ProductsDao
import com.example.androidapicall.Model.Local.Entities.ProductDetailEntity
import com.example.androidapicall.Model.Local.Entities.ProductListEntity


@Database(
    entities = [ProductListEntity::class, ProductDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    companion object {
        @Volatile
        private var
                INSTANCE: ProductsDatabase? = null

        fun getDatabase(context: Context): ProductsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java, "ProductsList"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}