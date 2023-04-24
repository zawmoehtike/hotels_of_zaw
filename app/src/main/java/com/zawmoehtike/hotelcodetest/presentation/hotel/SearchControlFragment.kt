package com.zawmoehtike.hotelcodetest.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.common.base.BaseFragment
import com.zawmoehtike.hotelcodetest.databinding.FragmentSearchControlBinding
import com.zawmoehtike.hotelcodetest.domain.models.LocationModel
import com.zawmoehtike.hotelcodetest.presentation.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SearchControlFragment: BaseFragment<FragmentSearchControlBinding>() {

    private var locationId: String = ""
    private var checkInDate: String = ""
    private var checkOutDate: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<LocationModel>("location")
            ?.observe(viewLifecycleOwner) { location ->
                locationId = location.id
                binding.etLocation.setText(location.title?:"")
            }

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val currentDatePlusOne = calendar.time

        val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedStartDate: String = formatter.format(Date())
        val formattedEndDate: String = formatter.format(currentDatePlusOne)
        val yyyyMMddFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val yyyyMMddFormatStartDate = yyyyMMddFormatter.format(Date())
        val yyyyMMddFormatEndDate = yyyyMMddFormatter.format(currentDatePlusOne)
        checkInDate = yyyyMMddFormatStartDate
        checkOutDate = yyyyMMddFormatEndDate
        binding.etStartDate.setText(formattedStartDate)
        binding.etEndDate.setText(formattedEndDate)

        binding.etLocation.setOnClickListener {
            findNavController().navigate(R.id.action_searchControlFragment_to_locationListFragment)
        }

        binding.btnSearch.setOnClickListener {
            if(locationId != "") {
                if (checkInDate != "") {
                    if (checkOutDate != "") {
                        val action =
                            SearchControlFragmentDirections.actionSearchControlFragmentToHotelListFragment()
                        action.provinceId = locationId
                        action.startDate = checkInDate
                        action.endDate = checkOutDate
                        findNavController().navigate(action)
                    } else {
                        requireContext().showToast("Pls Choose Check-out Date")
                    }
                } else {
                    requireContext().showToast("Pls Choose Check-in Date")
                }
            } else {
                requireContext().showToast("Pls Choose Location")
            }
        }

        binding.etStartDate.setOnClickListener {
            showDatePickerDialog(DATE_TYPE.START_DATE)
        }

        binding.etEndDate.setOnClickListener {
            showDatePickerDialog(DATE_TYPE.END_DATE)
        }
    }

    override fun bindView(inflater: LayoutInflater): FragmentSearchControlBinding {
        return FragmentSearchControlBinding.inflate(inflater)
    }

    private fun showDatePickerDialog(type: DATE_TYPE) {
        val calendarConstraints: CalendarConstraints.Builder = CalendarConstraints.Builder()
            .setStart(Calendar.getInstance().timeInMillis)
            .setValidator(DateValidatorPointForward.now())

        val datePicker: MaterialDatePicker<Long> = MaterialDatePicker
            .Builder
            .datePicker()
            .setCalendarConstraints(calendarConstraints.build())
            .setTitleText("Select date for event")
            .build()
        datePicker.isCancelable = false
        datePicker.show(childFragmentManager, "date")

        datePicker.addOnPositiveButtonClickListener {
            val simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy")
            val dateString = simpleDateFormat.format(
                Calendar.getInstance().apply {
                    timeInMillis = it
                }.time
            )

            val yyyyMMddFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val yyyyMMddFormat: String = yyyyMMddFormatter.format(
                Calendar.getInstance().apply {
                    timeInMillis = it
                }.time
            )

            if(type == DATE_TYPE.START_DATE) {
                checkInDate = yyyyMMddFormat
                binding.etStartDate.setText(dateString)
            } else if(type == DATE_TYPE.END_DATE) {
                checkOutDate = yyyyMMddFormat
                binding.etEndDate.setText(dateString)
            }
        }
    }
}

enum class DATE_TYPE {
    START_DATE,
    END_DATE
}