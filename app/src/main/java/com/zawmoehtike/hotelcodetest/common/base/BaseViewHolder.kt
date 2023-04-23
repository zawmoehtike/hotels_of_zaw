package com.zawmoehtike.hotelcodetest.common.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Vincent on 12/6/18
 * Copied and used by Zaw on 23/04/2023
 */
abstract class BaseViewHolder<itemType> protected constructor(
    itemView: View,
    val recyclerViewItemClickListener: RecyclerViewItemClickListener? = null
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: itemType)
}