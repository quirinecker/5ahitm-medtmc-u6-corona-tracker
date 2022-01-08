package com.example.coronatesttracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.coronatesttracker.CoronaTestListFragmentDirections
import com.example.coronatesttracker.R
import com.example.coronatesttracker.databinding.ListItemBinding
import com.example.coronatesttracker.model.CoronaTest
import com.example.coronatesttracker.model.CoronaTestResult
import java.time.format.DateTimeFormatter

class CoronaTestListAdapter(private val context: Context, private val tests: Array<CoronaTest>): BaseAdapter() {
    override fun getCount(): Int {
        return tests.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        convertView?.let { view ->
            val binding = ListItemBinding.bind(view)
            val test = tests[position]


            test.date?.let {
                binding.dateField.text = it.format(DateTimeFormatter.ISO_DATE_TIME)
            }

            test.location?.let {
                binding.placeField.text = it.name
            }

            binding.idField.text = test.id

            val colorId = when(test.result) {
                CoronaTestResult.POSITIVE -> R.color.positive
                CoronaTestResult.NEGATIVE -> R.color.negative
                null -> R.color.negative
            }

            binding.resultColorCode.setBackgroundColor(ResourcesCompat.getColor(
                context.resources,
                colorId,
                null
            ))
        }

        return convertView!!
    }

    fun onClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        view?.let {
            val test = tests[position]
            val navController = view.findNavController()
            val action = CoronaTestListFragmentDirections.actionCoronaTestListFragmentToInputScreenFragment(test)
            navController.navigate(action)
        }
    }

}