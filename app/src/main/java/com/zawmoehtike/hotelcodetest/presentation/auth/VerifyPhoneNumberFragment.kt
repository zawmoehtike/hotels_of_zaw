
package com.zawmoehtike.hotelcodetest.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentVerifyPhoneNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyPhoneNumberFragment: BaseFragment<FragmentVerifyPhoneNumberBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOK.setOnClickListener {
            findNavController().navigate(R.id.action_verifyPhoneNumberFragment_to_signUpFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentVerifyPhoneNumberBinding {
        return FragmentVerifyPhoneNumberBinding.inflate(inflater)
    }
}