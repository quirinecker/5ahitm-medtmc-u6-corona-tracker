package com.example.coronatesttracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        setBinding(view)
        setTestToEdit(args)
        setListeners()
        setInputs()

        return view
    }

    private fun setBinding(view: View?) {
        view?.let {
            binding = FragmentInputScreenBinding.bind(it)
        }
    }

    private fun setTestToEdit(args: InputScreenFragmentArgs) {
        test = args.coronaTest
    }

    private fun setListeners() {
        binding.saveButton.setOnClickListener {
            save()
        }
    }

    private fun save() {
        test.date = getDate()
        test.location = getLocation()
        test.result = getResult()

        returnToListView()
    }

    private fun getDate(): LocalDateTime {
        return LocalDateTime.parse(binding.dateField.text.toString(), dateTimeFormatter)
    }

    private fun getLocation(): Location {
        val selectedItemString = getSelectedSpinnerItem(binding.locationField)
        return getLocationByName(selectedItemString)
    }

    private fun getLocationByName(name: String): Location {
        return getLocations().first { it.name == name }
    }

    private fun getResult(): CoronaTestResult {
        return CoronaTestResult.valueOf(getSelectedSpinnerItem(binding.resultField))
    }

    private fun getSelectedSpinnerItem(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    private fun returnToListView() {
        findNavController().popBackStack()
    }

    private fun setInputs() {
        setId()
        setLocation()
        setDate()
        setResult()
    }

    private fun setId() {
        binding.idField.text = test.id
    }

    private fun setDate() {
        test.date?.let {
            val dateString = it.format(dateTimeFormatter)
            binding.dateField.setText(dateString)
        } ?: run {
            binding.dateField.setText("")
        }
    }

    private fun setLocation() {
        setDropDownSpinnerAdapter(getLocationNames(), binding.locationField)
        setLocationSpinnerSelection()
    }

    private fun setLocationSpinnerSelection() {
        test.location?.let {
            setCurrentLocationSelection(it)
        } ?: also {
            binding.locationField.setSelection(0)
        }
    }

    private fun setCurrentLocationSelection(test: Location) {
        val index = getLocationNames().indexOf(test.name)
        binding.locationField.setSelection(index)
    }

    private fun getLocations(): Array<Location> {
        return CoronaTest.locations
    }

    private fun getLocationNames(): Array<String> {
        return getLocations()
            .map { it.name }
            .toTypedArray()
    }

    private fun setResult() {
        setDropDownSpinnerAdapter(
            CoronaTestResult.values().map {
                it.toString()
            }.toTypedArray(),
            binding.resultField
        )

        binding.resultField.setSelection(getSelectionIndexOf(test))
    }

    private fun getSelectionIndexOf(
        test: CoronaTest
    ): Int {
        test.result?.let {
            return CoronaTestResult.values().indexOf(it)
        } ?: run {
            return CoronaTestResult.values().indexOf(CoronaTestResult.NEGATIVE)
        }
    }

    private fun setDropDownSpinnerAdapter(data: Array<String>, element: Spinner) {
        context?.let { context ->
            val adapter = getAdapterWith(context, data)
            setAdapterForSpinner(element, adapter)
        }
    }

    private fun setAdapterForSpinner(spinner: Spinner, adapter: ArrayAdapter<String>) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun getAdapterWith(context: Context, andData: Array<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            andData
        )
    }
}