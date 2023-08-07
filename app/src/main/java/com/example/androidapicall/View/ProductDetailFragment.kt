package com.example.androidapicall.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.androidapicall.PriceUtils
import com.example.androidapicall.R
import com.example.androidapicall.ViewModel.ProductsViewModel
import com.example.androidapicall.databinding.FragmentProductdetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductDetailFragment : Fragment() {

    private lateinit var bindingProductDetail: FragmentProductdetailBinding
    private val productViewModel: ProductsViewModel by activityViewModels()
    private var productId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingProductDetail = FragmentProductdetailBinding.inflate(inflater, container, false)
        return bindingProductDetail.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            productId = bundle.getInt("productId")
        }
        productId?.let { id -> productViewModel.fetchProductDetail(id) }

        productViewModel.getProductDetail().observe(viewLifecycleOwner, Observer { product ->

            Glide.get(requireContext()).clearMemory()
            Glide.with(bindingProductDetail.imgDetail)
                .load(product?.image)
                .into(bindingProductDetail.imgDetail)

            bindingProductDetail.itemDetail.text = product?.description
            bindingProductDetail.nameDetail.text = product?.name
            bindingProductDetail.priceDetail.text = PriceUtils.formattedPrice(product?.price ?: 0)
            val creditText = if (product?.credit == true) {
                "Credit card allowed"
            } else {
                "Only cash"
            }
            bindingProductDetail.creditDetail.text = creditText

            bindingProductDetail.buttonDetail.setOnClickListener {
                sendEmail(product?.id ?: -1, product?.name ?: "")
            }
        })


    }

    private fun sendEmail(id: Int, name: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("j.perezurrutia@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Solicitud información sobre producto $id")
            putExtra(
                Intent.EXTRA_TEXT,
                "Hola,\nQuisiera pedir información sobre este producto $name.\nMe gustaría que me contactaran a este correo."
            )
        }

        try {
            startActivity(emailIntent)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}