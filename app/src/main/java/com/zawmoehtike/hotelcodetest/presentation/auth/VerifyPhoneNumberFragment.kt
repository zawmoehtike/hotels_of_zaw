
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
import com.zawmoehtike.hotelcodetest.databinding.FragmentVerifyPhoneNumberBinding
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import com.zawmoehtike.hotelcodetest.presentation.utils.getLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyPhoneNumberFragment: BaseFragment<FragmentVerifyPhoneNumberBinding>() {

    private val viewModel by viewModels<VerifyPhoneNumberViewModel>()

    private var alertDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = getLoadingDialog()

        binding.tvSendToPhoneNumber.text = "Enter OTP sent to " + requireArguments().getString("countryCode", "") + requireArguments().getString("phoneNumber", "")

        binding.btnOK.setOnClickListener {
            viewModel.otpVerifyParams = VerifyPhoneNumberViewModel.OTPVerifyParams(
                phoneNumber = requireArguments().getString("phoneNumber", ""),
                countryCode = requireArguments().getString("countryCode", ""),
                otp = binding.etOTP.text.toString(),
            )
            viewModel.verifyOTP { state ->
                when(state) {
                    is ViewState.Success -> {
                        try {
                            alertDialog?.dismiss()

                            // todo: change response body of verify OTP
                            // todo: remove try catch
                            // todo: save token in share preference

                            val token = state.value

                            val action = SignUpFragmentDirections.actionSignUpFragmentToVerifyPhoneNumberFragment()
                            action.countryCode = requireArguments().getString("countryCode", "")
                            action.phoneNumber = requireArguments().getString("phoneNumber", "")
                            findNavController().navigate(action)
                        } catch (exception: java.lang.Exception) {
                            requireContext().showToast(exception.message.toString())

                            val action = SignUpFragmentDirections.actionSignUpFragmentToVerifyPhoneNumberFragment()
                            action.countryCode = requireArguments().getString("countryCode", "")
                            action.phoneNumber = requireArguments().getString("phoneNumber", "")
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

            // todo: remove the following code after all's done
            findNavController().navigate(R.id.action_verifyPhoneNumberFragment_to_searchControlFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentVerifyPhoneNumberBinding {
        return FragmentVerifyPhoneNumberBinding.inflate(inflater)
    }
}