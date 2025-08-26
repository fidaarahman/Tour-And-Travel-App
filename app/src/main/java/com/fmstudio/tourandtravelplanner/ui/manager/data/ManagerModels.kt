package com.fmstudio.tourandtravelplanner.ui.manager.data


data class Manager(
	val id: String = "",
	val name: String = "",
	val email: String = "",
	val phone: String = "",
	val cnic: String = ""
)


data class Room(
	val id: String = "",
	val roomNo: String = "",
	val beds: Int = 0,
	val washroom: Boolean = false,
	val status: String = "Pending"
)


data class BookingRequest(
	val id: String = "",
	val customerId: String = "",
	val roomId: String = "",
	val status: String = "Pending"
)


data class Customer(
	val id: String = "",
	val name: String = "",
	val email: String = "",
	val phone: String = "",
	val cnic: String = "",
	val roomNo: String = "",
	val persons: Int = 1,
	val beds: Int = 1,
	val washroom: Boolean = false
)


data class RequestDisplay(
	val requestId: String,
	val roomNo: String,
	val beds: Int,
	val washroom: Boolean,
	val status: String
)