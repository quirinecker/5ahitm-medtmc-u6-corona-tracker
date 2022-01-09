package com.example.coronatesttracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.navArgs
import com.example.coronatesttracker.databinding.FragmentOverviewBinding
import com.example.coronatesttracker.model.CoronaTest
import com.example.coronatesttracker.model.CoronaTestResult

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
        setLocationCountList()

        return view
    }

    private fun setLocationCountList() {
        val listData = ArrayList<String>()

        CoronaTest.locations.forEach { location ->
            val locationTestCount = coronaTestData
                .filter { it.location == location }
                .count()

            listData.add("${location.name}: $locationTestCount")
        }

        context?.let {
            val adapter = ArrayAdapter(it, android.R.layout.simple_list_item_1, listData)
            binding.testCountList.adapter = adapter
        }
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
        val positiveWidth = getWithFrom(positivePercentage)
        val negativeWidth = getWithFrom(negativePercentage)

        Log.d("positive", positiveWidth.toString())
        Log.d("negative", negativeWidth.toString())

        setPositiveAreaWidth(positiveWidth)
        setNegativeAreaWidth(negativeWidth)
    }

    private fun getWithFrom(percentage: Int): Float {
        return fullDiagramWidth * (percentage.toFloat() / 100)
    }

    private fun setNegativeAreaWidth(value: Float) {
        binding.negativeArea.updateLayoutParams<ConstraintLayout.LayoutParams> {
            width = (value * scale + 0.5f).toInt()
        }
    }

    private fun setPositiveAreaWidth(value: Float) {
        binding.positiveArea.updateLayoutParams<ConstraintLayout.LayoutParams> {
            width = (value * scale + 0.5f).toInt()
        }
    }

}