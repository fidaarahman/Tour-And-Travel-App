package com.fmstudio.tourandtravelplanner.ui.manager.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ManagerRepository(
	private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
	private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

	suspend fun getCurrentManager(): Manager? {
		val user = auth.currentUser ?: return null
		val doc = db.collection("managers").document(user.uid).get().await()
		return if (doc.exists()) doc.toObject(Manager::class.java)?.copy(id = user.uid) else null
	}

	suspend fun upsertManager(manager: Manager) {
		val user = auth.currentUser ?: throw IllegalStateException("Not authenticated")
		db.collection("managers").document(user.uid).set(manager.copy(id = user.uid)).await()
	}

	suspend fun addRoom(room: Room): String {
		val ref = db.collection("rooms").add(room).await()
		return ref.id
	}

	suspend fun getRooms(): List<Room> {
		val snapshot = db.collection("rooms").get().await()
		return snapshot.documents.mapNotNull { it.toObject(Room::class.java)?.copy(id = it.id) }
	}

	suspend fun getRoom(roomId: String): Room? {
		val doc = db.collection("rooms").document(roomId).get().await()
		return if (doc.exists()) doc.toObject(Room::class.java)?.copy(id = doc.id) else null
	}

	suspend fun updateRoomStatus(roomId: String, status: String) {
		db.collection("rooms").document(roomId).update(mapOf("status" to status)).await()
	}

	suspend fun getRequests(): List<BookingRequest> {
		val snapshot = db.collection("requests").get().await()
		return snapshot.documents.mapNotNull { it.toObject(BookingRequest::class.java)?.copy(id = it.id) }
	}

	suspend fun approveRequest(requestId: String) {
		db.collection("requests").document(requestId).update(mapOf("status" to "Approved")).await()
	}

	suspend fun getCustomers(): List<Customer> {
		val snapshot = db.collection("customers").get().await()
		return snapshot.documents.mapNotNull { it.toObject(Customer::class.java)?.copy(id = it.id) }
	}

	suspend fun getCustomer(customerId: String): Customer? {
		val doc = db.collection("customers").document(customerId).get().await()
		return if (doc.exists()) doc.toObject(Customer::class.java)?.copy(id = doc.id) else null
	}

	suspend fun approveCustomer(customerId: String) {
		db.collection("customers").document(customerId).update(mapOf("status" to "Approved")).await()
	}
}