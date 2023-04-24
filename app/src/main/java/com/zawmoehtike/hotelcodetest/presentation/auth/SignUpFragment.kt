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
import com.zawmoehtike.hotelcodetest.databinding.FragmentSignUpBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import com.zawmoehtike.hotelcodetest.presentation.utils.getLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {

    private var phoneNumber: String = ""
    private var countryCode: String = ""
    private val viewModel by viewModels<SignUpViewModel>()

    private var alertDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = getLoadingDialog()

        countryCode = requireArguments().getString("countryCode", "")
        phoneNumber = requireArguments().getString("phoneNumber", "")
        binding.etPhoneNumber.setText(countryCode + phoneNumber)

        binding.btnOK.setOnClickListener {
            viewModel.postRegisterParams = SignUpViewModel.PostRegisterParams(
                phoneNumber = phoneNumber,
                name = binding.etFullName.text.toString(),
                countryCode = countryCode,
                email = binding.etEmail.text.toString(),
            )
            viewModel.postRegister { state ->
                when(state) {
                    is ViewState.Success -> {
                        alertDialog?.dismiss()

                        val data = state.value

                        val action = SignUpFragmentDirections.actionSignUpFragmentToVerifyPhoneNumberFragment()
                        action.countryCode = countryCode
                        action.phoneNumber = phoneNumber
                        findNavController().navigate(action)
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

    override fun bindView(inflater: LayoutInflater): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater)
    }
}