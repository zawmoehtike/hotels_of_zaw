package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentHotelListBinding
import com.zawmoehtike.hotelcodetest.databinding.FragmentLocationListBinding

class LocationListFragment: BaseFragment<FragmentLocationListBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun bindView(inflater: LayoutInflater): FragmentLocationListBinding {
        return FragmentLocationListBinding.inflate(inflater)
    }
}