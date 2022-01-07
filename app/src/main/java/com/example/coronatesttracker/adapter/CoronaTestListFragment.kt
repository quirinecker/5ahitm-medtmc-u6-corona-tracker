package com.example.coronatesttracker.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coronatesttracker.R
import com.example.coronatesttracker.databinding.FragmentCoronaTestListBinding
import com.example.coronatesttracker.model.CoronaTest


class CoronaTestListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_corona_test_list, container, false)
        val binding = FragmentCoronaTestListBinding.bind(view);
        val sampleData = CoronaTest.sample;
        val locations = sampleData.map { it.location.name }

        return view
    }
}