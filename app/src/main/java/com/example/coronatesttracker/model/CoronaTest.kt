package com.example.coronatesttracker.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class CoronaTest(
    val id: Int,
    val date: LocalDateTime,
    val result: CoronaTestResult,
    val location: Location,
): Serializable {
    companion object {
        val sample = arrayOf(
            CoronaTest(0, LocalDateTime.now(), CoronaTestResult.NEGATIVE, Location.LEON),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(1, LocalDateTime.now(), CoronaTestResult.POSITIVE, Location.LINZ),
            CoronaTest(3, LocalDateTime.now(), CoronaTestResult.NEGATIVE, Location.HAID)
        )
    }
}