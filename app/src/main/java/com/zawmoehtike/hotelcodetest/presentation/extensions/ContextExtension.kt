package com.zawmoehtike.hotelcodetest.presentation.extensions

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast

fun Context.showToast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.inflater() : LayoutInflater {
    return LayoutInflater.from(this)
}
