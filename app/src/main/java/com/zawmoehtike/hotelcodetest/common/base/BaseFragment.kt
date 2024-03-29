package com.zawmoehtike.hotelcodetest.common.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): android.view.View? {
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        _binding = bindView(inflater)
        val view = binding.root
        return view
    }

    abstract fun bindView(inflater: LayoutInflater): VB

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("onDestroyView","called")
        _binding = null
    }
}