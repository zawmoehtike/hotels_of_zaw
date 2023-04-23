package com.zawmoehtike.hotelcodetest.presentation.hotel.adapters

import android.view.View
import android.view.ViewGroup
import com.zawmoehtike.hotelcodetest.common.base.BaseRecyclerViewAdapter
import com.zawmoehtike.hotelcodetest.common.base.BaseViewHolder
import com.zawmoehtike.hotelcodetest.common.base.diffCallBackWith
import com.zawmoehtike.hotelcodetest.databinding.ItemFacilityBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.inflater

class FacilityRecyclerAdapter (private val onClick : (String) -> Unit):
    BaseRecyclerViewAdapter<String, BaseViewHolder<String>>(diffCallback = diffCallBackWith(
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        },
        areItemTheSame = { item1, item2 ->
            item1 == item2
        }
    )) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val inflater = parent.context.inflater()
        val view = ItemFacilityBinding.inflate(inflater, parent, false).root

        return FacilityViewHolder(view, onClick)
    }

    inner class FacilityViewHolder(itemView: View, private val onClick: (String) -> Unit) : BaseViewHolder<String>(itemView) {
        override fun bind(item: String) {
            val binding = ItemFacilityBinding.bind(itemView)

            with(item) {
                with(binding) {
                    tvFacility.text = item
                }
            }
        }
    }
}