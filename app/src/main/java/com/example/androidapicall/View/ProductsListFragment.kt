package com.example.androidapicall.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.androidapicall.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidapicall.Adapter.ProductsAdapter
import com.example.androidapicall.ViewModel.ProductsViewModel
import com.example.androidapicall.databinding.FragmentProductlistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductsListFragment : Fragment() {

    private lateinit var bindingFragmentProductlist: FragmentProductlistBinding
    private val productViewModel: ProductsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingFragmentProductlist =
            FragmentProductlistBinding.inflate(inflater, container, false)
        return bindingFragmentProductlist.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductsAdapter()
        bindingFragmentProductlist.recyclerView.adapter = adapter
        bindingFragmentProductlist.recyclerView.layoutManager =
            GridLayoutManager(context, 2)
        productViewModel.getProductList().observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    Log.d("list products", it.toString())
                    adapter.update(it)
                }
            })

        adapter.selectedProduct().observe(viewLifecycleOwner) { product ->
            product?.let {
                Log.d("selected", product.id.toString())

                val bundle = Bundle().apply { putInt("productId", product.id) }
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()


    }
}