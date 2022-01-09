package com.example.coronatesttracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coronatesttracker.adapter.CoronaTestListAdapter
import com.example.coronatesttracker.databinding.FragmentCoronaTestListBinding
import com.example.coronatesttracker.model.CoronaTest
import java.time.format.DateTimeFormatter


class CoronaTestListFragment : Fragment() {

    private lateinit var binding: FragmentCoronaTestListBinding
    private lateinit var tests: MutableList<CoronaTest>
    private lateinit var adapter: CoronaTestListAdapter
    private var currentTestInCreation: CoronaTest? = null


    override fun onResume() {
        super.onResume()
        currentTestInCreation?.let {
            tests.add(it)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_corona_test_list, container, false)

        setBinding(view)
        setupListView()
        setupListeners()

        return view
    }

    private fun setupListeners() {
        binding.createButton.setOnClickListener {
            createTest()
        }

        binding.dailyOverviewButton.setOnClickListener {
            openDailyOverview()
        }
    }

    private fun createTest() {
        val testToCreate = CoronaTest()
        currentTestInCreation = testToCreate
        val action = CoronaTestListFragmentDirections
            .actionCoronaTestListFragmentToInputScreenFragment(testToCreate)
        findNavController().navigate(action)
    }

    private fun openDailyOverview() {
        TODO("Not yet implemented")
    }

    private fun setBinding(view: View) {
        binding = FragmentCoronaTestListBinding.bind(view)
    }

    private fun setupListView() {
        setupAdapter()
        setupListener()
    }

    private fun setupListener() {
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.onClick(parent, view, position, id)
        }
    }

    private fun setupAdapter() {
        createAdapter()?.let {
            binding.listView.adapter = it
        }
    }

    private fun createAdapter(): CoronaTestListAdapter? {
        context?.let {
            tests = CoronaTest.sample.toMutableList()
            adapter = CoronaTestListAdapter(it, tests)
            return adapter
        } ?: run {
            return null
        }
    }
}