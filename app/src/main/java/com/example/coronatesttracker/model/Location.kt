package com.example.coronatesttracker.model

data class Location(
    val name: String,
) {
    companion object {
        val LEON = Location("Leonding-Meixnerkreuzung")
        val HAID = Location("Parkplatz-Haidcenter")
        val LINZ = Location("Linz-Stadtplatz")
    }
}