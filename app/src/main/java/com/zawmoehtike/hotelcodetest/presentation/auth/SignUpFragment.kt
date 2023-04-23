package com.zawmoehtike.hotelcodetest.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentSignUpBinding
import com.zawmoehtike.hotelcodetest.databinding.FragmentVerifyPhoneNumberBinding

class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOK.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_hotelListFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater)
    }
}