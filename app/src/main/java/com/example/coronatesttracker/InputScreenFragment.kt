package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coronatesttracker.databinding.FragmentInputScreenBinding
import com.example.coronatesttracker.model.CoronaTest
import com.example.coronatesttracker.model.CoronaTestResult
import com.example.coronatesttracker.model.Location
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InputScreenFragment : Fragment() {

    private val args: InputScreenFragmentArgs by navArgs()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    private lateinit var binding: FragmentInputScreenBinding
    private lateinit var test: CoronaTest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input_screen, container, false)
        binding = FragmentInputScreenBinding.bind(view)

        setInputs()

        binding.saveButton.setOnClickListener {
            save()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun save() {
        args.coronaTest?.let {
            it.location = getPlace()
            it.result = getResult()
            it.date = getDate()

            returnToListView()
        }
    }

    private fun returnToListView() {
        findNavController().popBackStack()
    }

    private fun getResult(): CoronaTestResult {
        return CoronaTestResult.valueOf(binding.resultField.selectedItem.toString())
    }

    private fun getPlace(): Location {
        return Location(binding.placeField.text.toString())
    }

    private fun getDate(): LocalDateTime {
        return LocalDateTime.parse(binding.dateField.text.toString(), dateTimeFormatter)
    }

    private fun setInputs() {
        args.coronaTest?.let { test ->
            setId(test)
            setLocation(test)
            setDate(test)
            setResult(test)
        }
    }

    private fun setDate(test: CoronaTest) {
        val dateString = test.date.format(dateTimeFormatter)
        binding.dateField.setText(dateString)
    }

    private fun setLocation(test: CoronaTest) {
        binding.placeField.setText(test.location.name)
    }

    private fun setId(test: CoronaTest) {
        binding.idField.text = test.id.toString()
    }

    private fun setResult(test: CoronaTest) {
        context?.let {
            val coronaTestResultOptions = CoronaTestResult.values()

            val adapter = ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item,
                coronaTestResultOptions.map { entry -> entry.toString() }
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.resultField.adapter = adapter
            binding.resultField.setSelection(getSelectionIndexOf(test))
        }
    }

    private fun getSelectionIndexOf(
        test: CoronaTest
    ): Int {
        return CoronaTestResult.values().indexOf(test.result)
    }
}