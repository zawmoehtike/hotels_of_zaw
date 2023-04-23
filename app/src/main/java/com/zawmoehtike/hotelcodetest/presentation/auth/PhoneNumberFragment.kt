package com.zawmoehtike.hotelcodetest.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentPhoneNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberFragment : BaseFragment<FragmentPhoneNumberBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_phoneNumberFragment_to_verifyPhoneNumberFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentPhoneNumberBinding {
        return FragmentPhoneNumberBinding.inflate(inflater)
    }
}