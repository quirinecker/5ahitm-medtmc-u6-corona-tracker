package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.coronatesttracker.adapter.CoronaTestListAdapter
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

        context?.let {
            val adapter = CoronaTestListAdapter(it, sampleData)
            binding.listView.adapter = adapter

            binding.listView.setOnItemClickListener { parent, view, position, id ->
                adapter.onClick(parent, view, position, id)
            }
        }

        return view
    }
}