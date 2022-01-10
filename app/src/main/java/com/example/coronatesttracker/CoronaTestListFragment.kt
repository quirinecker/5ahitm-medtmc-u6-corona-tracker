package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coronatesttracker.adapter.CoronaTestListAdapter
import com.example.coronatesttracker.databinding.FragmentCoronaTestListBinding
import com.example.coronatesttracker.model.CoronaTest
import java.time.LocalDate


class CoronaTestListFragment : Fragment() {

    private lateinit var binding: FragmentCoronaTestListBinding
    private val tests: MutableList<CoronaTest> = CoronaTest.sample.toMutableList()
    private lateinit var adapter: CoronaTestListAdapter
    private var currentTestInCreation: CoronaTest? = null

    override fun onResume() {
        super.onResume()
        currentTestInCreation?.let {
            if (it.location != null && it.date != null && it.result != null) {
                tests.add(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_corona_test_list, container, false)

        setBinding(view)
        setupListeners()
        setupListView()

        return view
    }

    private fun setBinding(view: View) {
        binding = FragmentCoronaTestListBinding.bind(view)
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
        val action = CoronaTestListFragmentDirections
            .actionCoronaTestListFragmentToOverviewFragment(filterTestsByToday())

        findNavController().navigate(action)
    }

    private fun filterTestsByToday(): Array<CoronaTest> {
        return tests
            .filter { test -> test.date != null }
            .filter {test ->
                val dateEvaluated = test.date!!
                dateEvaluated.isAfter(LocalDate.now().atStartOfDay()) && dateEvaluated.isBefore(LocalDate.now().atTime(23, 59,59))
            }
            .toTypedArray()
    }

    private fun setupListView() {
        setupAdapter()
        setupItemClickListener()
    }

    private fun setupAdapter() {
        createAdapter()?.let {
            binding.listView.adapter = it
        }
    }

    private fun createAdapter(): CoronaTestListAdapter? {
        context?.let {
            adapter = CoronaTestListAdapter(it, tests)
            return adapter
        } ?: run {
            return null
        }
    }

    private fun setupItemClickListener() {
        binding.listView.setOnItemClickListener { _, view, position, _ ->
            adapter.onClick(view, position)
        }
    }

}