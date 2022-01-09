package com.example.coronatesttracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class CoronaTest(
    val id: String = nextId(),
    var date: LocalDateTime? = null,
    var result: CoronaTestResult? = null,
    var location: Location? = null,
): Serializable, Parcelable {
    companion object {
        private var currentIdNumber = 1000
        val locations = arrayOf(
            Location("MEIX","Leonding-Meixnerkreuzung"),
            Location("HAID", "Parkplatz-Haidcenter"),
            Location("LINZ","Linz-Stadtplatz")
        )

        fun nextId(): String {
            val idString = "OÖ…$currentIdNumber"
            currentIdNumber++
            return idString
        }

        val sample = arrayOf(
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation()),
            CoronaTest(date = LocalDateTime.now(), result = getRandomResult(), location = getRandomLocation())
        )

        private fun getRandomLocation(): Location {
            val randomIndex = getRandomIndexOf(locations.size)
            return locations[randomIndex]
        }

        private fun getRandomResult(): CoronaTestResult {
            val randomIndex = getRandomIndexOf(CoronaTestResult.values().size)
            return CoronaTestResult.values()[randomIndex]
        }

        private fun getRandomIndexOf(listCount: Int): Int {
            val random = Random()
            return (random.nextInt(listCount))
        }
    }
}