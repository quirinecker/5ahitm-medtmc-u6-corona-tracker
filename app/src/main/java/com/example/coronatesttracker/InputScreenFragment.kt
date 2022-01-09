package com.example.coronatesttracker

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
import java.lang.reflect.Modifier
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaGetter

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
        setInputs()
        setListeners()

        return view
    }

    private fun setTestToEdit(args: InputScreenFragmentArgs) {
        test = args.coronaTest
    }

    private fun setBinding(view: View?) {
        view?.let {
            binding = FragmentInputScreenBinding.bind(it)
        }
    }

    private fun setListeners() {
        binding.saveButton.setOnClickListener {
            save()
        }
    }

    private fun save() {
        test.location = getPlace()
        test.result = getResult()
        test.date = getDate()

        returnToListView()
    }

    private fun returnToListView() {
        findNavController().popBackStack()
    }

    private fun getResult(): CoronaTestResult {
        return CoronaTestResult.valueOf(getSelectedSpinnerItem(binding.resultField))
    }

    private fun getPlace(): Location {
        val selectedItemString = getSelectedSpinnerItem(binding.locationField)
        return getLocationByName(selectedItemString)
    }

    private fun getLocationByName(name: String): Location {
        return getLocations().first { it.name == name }
    }

    private fun getSelectedSpinnerItem(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    private fun getDate(): LocalDateTime {
        return LocalDateTime.parse(binding.dateField.text.toString(), dateTimeFormatter)
    }

    private fun setInputs() {
        setId(test)
        setLocation(test)
        setDate(test)
        setResult(test)
    }

    private fun setDate(test: CoronaTest) {
        test.date?.let {
            val dateString = it.format(dateTimeFormatter)
            binding.dateField.setText(dateString)
        } ?: run {
            binding.dateField.setText("")
        }
    }

    private fun setLocation(test: CoronaTest) {
        context?.let { context ->
            val adapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                getLocationNames()
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.locationField.adapter = adapter

            test.location?.let {
                setCurrentSelection(it)
            } ?: also {
                binding.locationField.setSelection(0)
            }
        }
    }

    private fun setCurrentSelection(test: Location) {
        val index = getLocationNames().indexOf(test.name)
        binding.locationField.setSelection(index)
    }

    private fun getLocationNames(): Array<String> {
        return getLocations()
            .map { it.name }
            .toTypedArray()
    }

    private fun getLocations(): Array<Location> {
        return Location::class
            .companionObject!!
            .memberProperties
            .filter { isFieldAccessible(it) }
            .map { it.getter.call(Location.Companion) as Location }
            .toTypedArray()
    }

    private fun isFieldAccessible(property: KProperty1<*, *>): Boolean {
        return property.javaGetter?.modifiers?.let {
            !Modifier.isPrivate(it)
        } ?: false
    }

    private fun setId(test: CoronaTest) {
        binding.idField.text = test.id
    }

    private fun setResult(test: CoronaTest) {
        context?.let {
            val coronaTestResultOptions = CoronaTestResult.values()

            val adapter = ArrayAdapter(
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
        test.result?.let {
            return CoronaTestResult.values().indexOf(it)
        } ?: run {
            return CoronaTestResult.values().indexOf(CoronaTestResult.NEGATIVE)
        }
    }
}