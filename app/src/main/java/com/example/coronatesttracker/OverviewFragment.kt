package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.navArgs
import com.example.coronatesttracker.databinding.FragmentOverviewBinding
import com.example.coronatesttracker.model.CoronaTest
import com.example.coronatesttracker.model.CoronaTestResult
import com.example.coronatesttracker.model.Location

class OverviewFragment : Fragment() {

    private val arguments: OverviewFragmentArgs by navArgs()
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var coronaTestData: Array<CoronaTest>
    private var scale: Float = 1f
    private val fullDiagramWidth = 350


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.let {
            scale = it.resources.displayMetrics.density
        }

        coronaTestData = arguments.coronaTests
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        binding = FragmentOverviewBinding.bind(view)

        setDiagramData()
        setLocationCountListData()

        return view
    }

    private fun setDiagramData() {
        val numberOfPositiveTests = getNumberOfTestsWhichAre(CoronaTestResult.POSITIVE)
        val numberOfNegativeTests = getNumberOfTestsWhichAre(CoronaTestResult.NEGATIVE)
        val numberOfTotalTests = numberOfNegativeTests + numberOfPositiveTests

        setDiagram(
            numberOfPositiveTests * 100 / numberOfTotalTests,
            numberOfNegativeTests * 100 / numberOfTotalTests
        )
    }

    private fun getNumberOfTestsWhichAre(result: CoronaTestResult): Int {
        return arguments.coronaTests
            .filter { it.result == result }
            .count()
    }

    private fun setDiagram(positivePercentage: Int, negativePercentage: Int) {
        val positiveWidth = getWidthFor(positivePercentage)
        val negativeWidth = getWidthFor(negativePercentage)

        setPositiveAreaWidth(positiveWidth)
        setNegativeAreaWidth(negativeWidth)
    }


    private fun getWidthFor(percentage: Int): Float {
        return fullDiagramWidth * (percentage.toFloat() / 100)
    }

    private fun setPositiveAreaWidth(value: Float) {
        setWidthFor(binding.positiveArea, value)
    }

    private fun setNegativeAreaWidth(value: Float) {
        setWidthFor(binding.negativeArea, value)
    }

    private fun setWidthFor(view: View, withWidth: Float) {
        view.updateLayoutParams<ViewGroup.LayoutParams> {
            width = (withWidth * scale + 0.5f).toInt()
        }
    }

    private fun setLocationCountListData() {
        val listData = getListData()
        setListData(listData)
    }

    private fun getListData(): Array<String> {
        val listData = ArrayList<String>()

        CoronaTest.locations.forEach { location ->
            val locationTestCount = getTestsBy(location).size
            listData.add("${location.name}: $locationTestCount")
        }

        return listData.toTypedArray()
    }

    private fun getTestsBy(location: Location): Array<CoronaTest> {
        return coronaTestData
            .filter { it.location == location }
            .toTypedArray()
    }

    private fun setListData(listData: Array<String>) {
        context?.let {
            val adapter = ArrayAdapter(it, android.R.layout.simple_list_item_1, listData)
            binding.testCountList.adapter = adapter
        }
    }

}