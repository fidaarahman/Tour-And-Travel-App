package com.fmstudio.tourandtravelplanner.helper

data class HotelRequest(
    val    hotelName: String,
    val location: String,
    val status: String,
    val imageResId: Int // reference to drawable
)
