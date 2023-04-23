package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.databinding.FragmentLocationListBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import com.zawmoehtike.hotelcodetest.presentation.hotel.adapters.HotelRecyclerAdapter
import com.zawmoehtike.hotelcodetest.presentation.hotel.adapters.LocationRecyclerAdapter
import com.zawmoehtike.hotelcodetest.presentation.utils.getLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationListFragment: BaseFragment<FragmentLocationListBinding>() {

    private val viewModel by viewModels<LocationListViewModel>()

    private var alertDialog: AlertDialog? = null
    private val adapter by lazy {
        LocationRecyclerAdapter(
            onClick = {

            }
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = getLoadingDialog()

        binding.rvLocations.adapter = adapter

        viewModel.getLocationsList { state ->
            when(state) {
                is ViewState.Success -> {
                    alertDialog?.dismiss()
                    adapter.submitList(state.value)
                }
                is ViewState.Error -> {
                    alertDialog?.dismiss()
                    requireContext().showToast(state.errorMessage)
                }
                is ViewState.Loading -> {
                    alertDialog?.show()
                }
                else -> {

                }
            }
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentLocationListBinding {
        return FragmentLocationListBinding.inflate(inflater)
    }
}