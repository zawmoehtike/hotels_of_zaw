package com.zawmoehtike.hotelcodetest.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOK.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_searchControlFragment)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater)
    }
}