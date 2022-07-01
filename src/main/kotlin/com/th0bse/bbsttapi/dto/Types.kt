package com.th0bse.bbsttapi.dto

enum class Weekday {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum class Hour(
    val startTime: String,
    val endTime: String
) {
    ONE("7:55", ""),
    TWO("", ""),
    THREE("", ""),
    FOUR("", ""),
    FIVE("", ""),
    SIX("", ""),
    SEVEN("", ""),
    EIGHT("", ""),
    NINE("", "")


}
