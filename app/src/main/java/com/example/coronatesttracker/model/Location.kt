package com.example.coronatesttracker.model

import java.io.Serializable

data class Location(
    val name: String,
): Serializable {
    companion object {
        val LEON = Location("Leonding-Meixnerkreuzung")
        val HAID = Location("Parkplatz-Haidcenter")
        val LINZ = Location("Linz-Stadtplatz")
    }
}