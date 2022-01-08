package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coronatesttracker.adapter.CoronaTestListAdapter
import com.example.coronatesttracker.databinding.FragmentCoronaTestListBinding
import com.example.coronatesttracker.model.CoronaTest


class CoronaTestListFragment : Fragment() {

    private lateinit var binding: FragmentCoronaTestListBinding

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
        adapter?.let {
            binding.listView.setOnItemClickListener { parent, view, position, id ->
                it.onClick(parent, view, position, id)
            }
        }
    }

    private fun setupAdapter() {
        adapter?.let {
            binding.listView.adapter = it
        }
    }

    private val adapter: CoronaTestListAdapter?
            get() {
                context?.let {
                    val sampleData = CoronaTest.sample
                    return CoronaTestListAdapter(it, sampleData)
                } ?: run {
                    return null
                }
            }
}