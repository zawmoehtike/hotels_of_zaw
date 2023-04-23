package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.databinding.FragmentHotelDetailBinding
import com.zawmoehtike.hotelcodetest.databinding.FragmentHotelListBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import com.zawmoehtike.hotelcodetest.presentation.hotel.adapters.FacilityRecyclerAdapter
import com.zawmoehtike.hotelcodetest.presentation.hotel.adapters.LocationRecyclerAdapter
import com.zawmoehtike.hotelcodetest.presentation.utils.getLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelDetailFragment: BaseFragment<FragmentHotelDetailBinding>() {

    var hotelId: String = ""
    private val viewModel by viewModels<HotelDetailViewModel>()

    private var alertDialog: AlertDialog? = null
    private val adapter by lazy {
        FacilityRecyclerAdapter(
            onClick = {

            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = getLoadingDialog()

        binding.rvFacility.adapter = adapter

        hotelId = requireArguments().getString("hotelId", "")

        viewModel.getHotelDetailsParams = HotelDetailViewModel.GetHotelDetailsParams(
            hotelId = hotelId
        )
        viewModel.getHotelDetails { state ->
            when(state) {
                is ViewState.Success -> {
                    alertDialog?.dismiss()

                    val details = state.value

                    with(binding) {
                        Glide.with(ivHotel)
                            .load(details.imageUrl)
                            .placeholder(R.drawable.ic_broken_image)
                            .centerCrop()
                            .into(ivHotel)

                        adapter.submitList(details.facilitiesList)

                        ratingBar.rating = details.reviewCount.toFloat()

                        tvTitle.text = details.title
                        tvSubTitle.text = details.description
                        tvPrice.text = details.priceRange
                        tvCheckIn.text = details.checkInDate
                        tvCheckOut.text = details.checkOutDate
                        tvPopularityScoreValue.text = details.popularityScore
                    }
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

    override fun bindView(inflater: LayoutInflater): FragmentHotelDetailBinding {
        return FragmentHotelDetailBinding.inflate(inflater)
    }
}