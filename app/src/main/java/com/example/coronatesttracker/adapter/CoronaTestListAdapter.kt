package com.example.coronatesttracker.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import com.example.coronatesttracker.CoronaTestListFragmentDirections
import com.example.coronatesttracker.R
import com.example.coronatesttracker.databinding.ListItemBinding
import com.example.coronatesttracker.model.CoronaTest
import com.example.coronatesttracker.model.CoronaTestResult
import java.time.format.DateTimeFormatter

class CoronaTestListAdapter(private val context: Context, private val tests: List<CoronaTest>): BaseAdapter() {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    private lateinit var binding: ListItemBinding

    override fun getCount(): Int {
        Log.i(this::class.simpleName, tests.size.toString())
        return tests.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val test = tests[position]
        val evaluatedView = evaluateConvertView(convertView, parent)
        binding = ListItemBinding.bind(evaluatedView)


        setId(test)
        setLocation(test)
        setDate(test)
        setResult(test)

        return evaluatedView
    }

    private fun evaluateConvertView(view: View?, parent: ViewGroup?): View {
        return view ?: LayoutInflater.from(context).inflate(
            R.layout.list_item,
            parent,
            false
        )
    }

    private fun setId(test: CoronaTest) {
        binding.idField.text = test.id
    }

    private fun setLocation(test: CoronaTest) {
        test.location?.let {
            binding.placeField.text = it.name
        }
    }

    private fun setDate(test: CoronaTest) {
        test.date?.let {
            binding.dateField.text = it.format(dateTimeFormatter)
        }
    }

    private fun setResult(test: CoronaTest) {
        val colorId = getColorCode(test)
        setBackgroundColor(colorId, binding.resultColorCode)
    }

    private fun getColorCode(test: CoronaTest): Int {
        return when(test.result) {
            CoronaTestResult.POSITIVE -> R.color.positive
            CoronaTestResult.NEGATIVE -> R.color.negative
            null -> R.color.negative
        }
    }

    private fun setBackgroundColor(colorId: Int, of: View) {
        of.setBackgroundColor(ResourcesCompat.getColor(
            context.resources,
            colorId,
            null
        ))
    }

    fun onClick(view: View?, position: Int) {
        view?.let {
            val test = tests[position]
            val navController = view.findNavController()
            val action = CoronaTestListFragmentDirections
                .actionCoronaTestListFragmentToInputScreenFragment(test)

            navController.navigate(action)
        }
    }

}