package com.example.androidapicall.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidapicall.Model.Local.Entities.ProductListEntity
import com.example.androidapicall.PriceUtils
import com.example.androidapicall.databinding.CardItemListBinding
import com.example.androidapicall.databinding.FragmentProductlistBinding

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var productList = listOf<ProductListEntity>()
    private val selectedProduct = MutableLiveData<ProductListEntity>()

    fun update(list: List<ProductListEntity>) {
        productList = list
        notifyDataSetChanged()
    }

    fun selectedProduct(): LiveData<ProductListEntity> = selectedProduct

    inner class ProductViewHolder(private val cardItemListBinding: CardItemListBinding) :
        RecyclerView.ViewHolder(
            cardItemListBinding.root
        ), View.OnClickListener {

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductListEntity) {
            Glide.with(cardItemListBinding.imageListProduct).load(product.image).centerCrop()
                .into(cardItemListBinding.imageListProduct)
            cardItemListBinding.nameItemList.text = product.name
            cardItemListBinding.priceItemList.text = PriceUtils.formattedPrice(product.price)
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            selectedProduct.value = productList[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(CardItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

}