package com.example.coronatesttracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.time.LocalDateTime

@Parcelize
data class CoronaTest(
    val id: String = nextId(),
    var date: LocalDateTime? = null,
    var result: CoronaTestResult? = null,
    var location: Location? = null,
): Serializable, Parcelable {
    companion object {
        private var currentIdNumber = 1000

        fun nextId(): String {
            val idString = "OÖ…$currentIdNumber"
            currentIdNumber++
            return idString
        }

        val sample = arrayOf(
            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.NEGATIVE, location = Location.LEON),
            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.POSITIVE, location = Location.LINZ),
//            CoronaTest(date = LocalDateTime.now(), result = CoronaTestResult.NEGATIVE, location = Location.HAID)
        )
    }
}