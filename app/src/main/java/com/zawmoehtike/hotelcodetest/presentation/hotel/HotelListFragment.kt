package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.databinding.FragmentHotelListBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import com.zawmoehtike.hotelcodetest.presentation.hotel.adapters.HotelRecyclerAdapter
import com.zawmoehtike.hotelcodetest.presentation.utils.getLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelListFragment: BaseFragment<FragmentHotelListBinding>() {

    private val viewModel by viewModels<HotelListViewModel>()

    private var alertDialog: AlertDialog? = null
    private val adapter by lazy {
        HotelRecyclerAdapter(
            onClick = {
                val action = HotelListFragmentDirections.actionHotelListFragmentToHotelDetailFragment()
                action.hotelId = it.id
                findNavController().navigate(action)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = getLoadingDialog()

        binding.rvHotels.adapter = adapter

        viewModel.getHotelsList { state ->
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

        binding.ivFilter.setOnClickListener {
            findNavController().navigate(R.id.action_hotelListFragment_to_searchControlFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentHotelListBinding {
        return FragmentHotelListBinding.inflate(inflater)
    }
}