package com.fmstudio.tourandtravelplanner.ui.manager.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ManagerProfileViewModel(private val repository: ManagerRepository = ManagerRepository()) : ViewModel() {

	private val _manager = MutableLiveData<Manager?>()
	val manager: LiveData<Manager?> = _manager

	fun loadProfile() {
		viewModelScope.launch {
			_manager.value = repository.getCurrentManager()
		}
	}

	fun saveProfile(manager: Manager) {
		viewModelScope.launch {
			repository.upsertManager(manager)
			_manager.value = manager
		}
	}
}

class RoomsViewModel(private val repository: ManagerRepository = ManagerRepository()) : ViewModel() {

	private val _rooms = MutableLiveData<List<Room>>()
	val rooms: LiveData<List<Room>> = _rooms

	fun loadRooms() {
		viewModelScope.launch {
			_rooms.value = repository.getRooms()
		}
	}
}

class RequestsViewModel(private val repository: ManagerRepository = ManagerRepository()) : ViewModel() {

	private val _requests = MutableLiveData<List<RequestDisplay>>()
	val requests: LiveData<List<RequestDisplay>> = _requests

	fun loadRequests() {
		viewModelScope.launch {
			val reqs = repository.getRequests()
			val displays = mutableListOf<RequestDisplay>()
			for (r in reqs) {
				val room = r.roomId.takeIf { it.isNotEmpty() }?.let { repository.getRoom(it) }
				displays += RequestDisplay(
					requestId = r.id,
					roomNo = room?.roomNo ?: r.roomId,
					beds = room?.beds ?: 0,
					washroom = room?.washroom ?: false,
					status = r.status
				)
			}
			_requests.value = displays
		}
	}

	fun approve(requestId: String) {
		viewModelScope.launch { repository.approveRequest(requestId); loadRequests() }
	}
}

class CustomersViewModel(private val repository: ManagerRepository = ManagerRepository()) : ViewModel() {

	private val _customers = MutableLiveData<List<Customer>>()
	val customers: LiveData<List<Customer>> = _customers

	fun loadCustomers() {
		viewModelScope.launch { _customers.value = repository.getCustomers() }
	}
}