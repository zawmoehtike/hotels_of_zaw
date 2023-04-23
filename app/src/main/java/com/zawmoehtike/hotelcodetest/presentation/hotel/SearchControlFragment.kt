package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentSearchControlBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchControlFragment: BaseFragment<FragmentSearchControlBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etLocation.setOnClickListener {
            findNavController().navigate(R.id.action_searchControlFragment_to_locationListFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentSearchControlBinding {
        return FragmentSearchControlBinding.inflate(inflater)
    }
}