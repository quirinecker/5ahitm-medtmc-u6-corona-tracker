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
        return CoronaTestResult.valueOf(binding.resultField.selectedItem.toString())
    }

    private fun getPlace(): Location {
        return Location(binding.placeField.text.toString())
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
        test.location?.let {
            binding.placeField.setText(it.name)
        } ?: run{
            binding.placeField.setText("")
        }
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