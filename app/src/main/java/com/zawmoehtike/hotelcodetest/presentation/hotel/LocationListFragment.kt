package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentLocationListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationListFragment: BaseFragment<FragmentLocationListBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun bindView(inflater: LayoutInflater): FragmentLocationListBinding {
        return FragmentLocationListBinding.inflate(inflater)
    }
}