package com.fmstudio.tourandtravelplanner.helper

data class TransportRequest(
    val transportName: String,
    val location: String,
    val status: String,
    val imageResId: Int // reference to drawable
)
