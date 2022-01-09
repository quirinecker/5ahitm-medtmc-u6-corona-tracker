package com.example.coronatesttracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.coronatesttracker.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val arguments: OverviewFragmentArgs by navArgs()
    private lateinit var binding: FragmentOverviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(this::class.simpleName, arguments.coronaTests.size.toString())
        binding = FragmentOverviewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

}