package com.zawmoehtike.hotelcodetest.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.databinding.FragmentPhoneNumberBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import com.zawmoehtike.hotelcodetest.presentation.utils.getLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberFragment : BaseFragment<FragmentPhoneNumberBinding>() {

    private val locale: String = "TH"
    private val viewModel by viewModels<PhoneNumberViewModel>()

    private var alertDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = getLoadingDialog()

        binding.btnNext.setOnClickListener {
            viewModel.postLoginParams = PhoneNumberViewModel.PostLoginParams(
                phoneNumber = binding.etPhoneNumber.text.toString(),
                countryCode = binding.tvCountryFlag.text.toString(),
                locale = locale
            )
            viewModel.postLogin { state ->
                when(state) {
                    is ViewState.Success -> {
                        alertDialog?.dismiss()

                        val data = state.value

                        if(data.contains("otp")) {
                            val action = PhoneNumberFragmentDirections.actionPhoneNumberFragmentToVerifyPhoneNumberFragment()
                            action.countryCode = binding.tvCountryFlag.text.toString()
                            action.phoneNumber = binding.etPhoneNumber.text.toString()
                            findNavController().navigate(action)
                        } else {
                            val action = PhoneNumberFragmentDirections.actionPhoneNumberFragmentToSignUpFragment()
                            action.countryCode = binding.tvCountryFlag.text.toString()
                            action.phoneNumber = binding.etPhoneNumber.text.toString()
                            findNavController().navigate(action)
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
    }

    override fun bindView(inflater: LayoutInflater): FragmentPhoneNumberBinding {
        return FragmentPhoneNumberBinding.inflate(inflater)
    }
}