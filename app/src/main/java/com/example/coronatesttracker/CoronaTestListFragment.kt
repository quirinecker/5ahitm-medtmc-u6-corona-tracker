package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.coronatesttracker.databinding.FragmentCoronaTestListBinding
import com.example.coronatesttracker.model.CoronaTest
import java.time.format.DateTimeFormatter


class CoronaTestListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_corona_test_list, container, false)
        val binding = FragmentCoronaTestListBinding.bind(view);
        val sampleData = CoronaTest.sample;
        val arrayAdapterValues = sampleData.map {
            val dateString = it.date.format(DateTimeFormatter.ISO_DATE_TIME)
            val locationName = it.location.name

            val locationString = locationName.substring(0,
                locationName.length.coerceAtMost(10)
            )

            if (locationName.length > 10) {
                "${it.id}: ${dateString}: ${locationString}...: ${it.result}"
            } else {
                "${it.id}: ${dateString}: ${locationString}: ${it.result}"
            }
        }

        context?.let {
            val adapter = ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, arrayAdapterValues)
            binding.listView.adapter = adapter
        }

        return view
    }
}