package com.zawmoehtike.hotelcodetest.presentation.hotel.adapters

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseRecyclerViewAdapter
import com.zawmoehtike.hotelcodetest.common.base.BaseViewHolder
import com.zawmoehtike.hotelcodetest.common.base.diffCallBackWith
import com.zawmoehtike.hotelcodetest.databinding.ItemLocationBinding
import com.zawmoehtike.hotelcodetest.domain.models.LocationModel
import com.zawmoehtike.hotelcodetest.presentation.extensions.inflater

class LocationRecyclerAdapter (private val onClick : (LocationModel) -> Unit):
    BaseRecyclerViewAdapter<LocationModel, BaseViewHolder<LocationModel>>(diffCallback = diffCallBackWith(
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        },
        areItemTheSame = { item1, item2 ->
            item1 == item2
        }
    )) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LocationModel> {
        val inflater = parent.context.inflater()
        val view = ItemLocationBinding.inflate(inflater, parent, false).root

        return LocationViewHolder(view, onClick)
    }

    inner class LocationViewHolder(itemView: View, private val onClick: (LocationModel) -> Unit) : BaseViewHolder<LocationModel>(itemView) {
        override fun bind(item: LocationModel) {
            val binding = ItemLocationBinding.bind(itemView)

            with(item) {
                with(binding) {
                    if(id == "0") {
                        Glide.with(ivLocation.context)
                            .load(R.drawable.ic_gps_grey)
                            .placeholder(R.drawable.ic_broken_image)
                            .centerCrop()
                            .into(ivLocation)
                    } else if(id == "-1") {
                        Glide.with(ivLocation.context)
                            .load(R.drawable.ic_heart_pink)
                            .placeholder(R.drawable.ic_broken_image)
                            .centerCrop()
                            .into(ivLocation)
                    } else {
                        Glide.with(ivLocation.context)
                            .load(R.drawable.ic_location_grey)
                            .placeholder(R.drawable.ic_broken_image)
                            .centerCrop()
                            .into(ivLocation)
                    }

                    tvTitle.text = item.title
                    tvSubTitle.text = item.subTitle
                }
            }
        }
    }
}