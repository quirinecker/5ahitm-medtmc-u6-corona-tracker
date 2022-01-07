package com.example.coronatesttracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.res.ResourcesCompat
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

        convertView?.let {
            val binding = ListItemBinding.bind(it)
            val test = tests[position]

            binding.dateField.text = tests[position].date.format(DateTimeFormatter.ISO_DATE_TIME)
            binding.idField.text = test.id.toString()
            binding.placeField.text = test.location.name

            val colorId = when(test.result) {
                CoronaTestResult.POSITIVE -> R.color.positive
                CoronaTestResult.NEGATIVE -> R.color.negative
            }

            binding.resultColorCode.setBackgroundColor(ResourcesCompat.getColor(
                context.resources,
                colorId,
                null
            ))
        }

        return convertView!!
    }

}