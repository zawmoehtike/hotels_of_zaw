package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentHotelListBinding
import com.zawmoehtike.hotelcodetest.databinding.FragmentSearchControlBinding

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