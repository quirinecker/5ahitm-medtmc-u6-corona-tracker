package com.example.coronatesttracker.model

import java.util.*

data class CoronaTest(
    val id: Int,
    val date: Date,
    val result: CoronaTestResult,
    val location: Location,
) {
    companion object {
        val sample = arrayOf(
            CoronaTest(0, Date(), CoronaTestResult.NEGATIVE, Location.LEON),
            CoronaTest(1, Date(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(3, Date(), CoronaTestResult.NEGATIVE, Location.HAID)
        )
    }
}