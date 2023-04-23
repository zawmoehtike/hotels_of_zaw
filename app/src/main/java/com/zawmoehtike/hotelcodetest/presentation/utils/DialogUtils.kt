package com.zawmoehtike.hotelcodetest.presentation.utils

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zawmoehtike.hotelcodetest.R

fun Context.getLoadingDialog(): AlertDialog {
    return MaterialAlertDialogBuilder(this, R.style.AlertDialogStyle)
        .setView(R.layout.layout_loading)
        .setCancelable(false)
        .create()
}

fun Fragment.getLoadingDialog(): AlertDialog {
    return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogStyle)
        .setView(R.layout.layout_loading)
        .setCancelable(false)
        .create()
}

fun Activity.getLoadingDialog(): AlertDialog {
    return MaterialAlertDialogBuilder(this, R.style.AlertDialogStyle)
        .setView(R.layout.layout_loading)
        .setCancelable(false)
        .create()
}