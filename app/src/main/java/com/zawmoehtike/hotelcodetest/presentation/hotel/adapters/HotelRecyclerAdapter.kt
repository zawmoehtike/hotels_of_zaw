package com.zawmoehtike.hotelcodetest.presentation.hotel.adapters

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseRecyclerViewAdapter
import com.zawmoehtike.hotelcodetest.common.base.BaseViewHolder
import com.zawmoehtike.hotelcodetest.common.base.diffCallBackWith
import com.zawmoehtike.hotelcodetest.databinding.ItemHotelBinding
import com.zawmoehtike.hotelcodetest.domain.models.HotelModel
import com.zawmoehtike.hotelcodetest.presentation.extensions.inflater

class HotelRecyclerAdapter (private val onClick : (HotelModel) -> Unit):
    BaseRecyclerViewAdapter<HotelModel, BaseViewHolder<HotelModel>>(diffCallback = diffCallBackWith(
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        },
        areItemTheSame = { item1, item2 ->
            item1 == item2
        }
    )) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HotelModel> {
        val inflater = parent.context.inflater()
        val view = ItemHotelBinding.inflate(inflater, parent, false).root

        return HotelViewHolder(view, onClick)
    }

    inner class HotelViewHolder(itemView: View, private val onClick: (HotelModel) -> Unit) : BaseViewHolder<HotelModel>(itemView) {
        override fun bind(item: HotelModel) {
            val binding = ItemHotelBinding.bind(itemView)

            with(item) {
                with(binding) {
                    tvTitle.text = name
                    tvSubTitle.text = description
                    tvPrice.text = priceRange
                    chipReview.text = reviewCount

                    Glide.with(ivLocation.context)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_broken_image)
                        .centerCrop()
                        .into(ivLocation)

                    ivLocation.setOnClickListener {
                        onClick(item)
                    }
                }
            }
        }
    }
}