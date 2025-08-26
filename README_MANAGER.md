# Hotel Manager Module

Features implemented:

- Authentication via Firebase Auth; managers identified by a document in `managers/{uid}`
- Manager Dashboard with bottom navigation (Home, Requests, Profile)
- Add Room screen: Room No, Beds, Washroom, Status; saved to `rooms`
- Rooms screen: lists all rooms with Approve/Add Room actions
- Requests screen: lists booking requests from `requests` and shows room details; Approve updates status to "Approved"
- Profile screen: loads and edits manager profile in `managers`
- Customers list and Customer Details screens reading from `customers`

Firebase collections:

- managers/{managerId}: { name, email, phone, cnic }
- rooms/{roomId}: { roomNo, beds, washroom, status }
- requests/{requestId}: { customerId, roomId, status }
- customers/{customerId}: { name, email, phone, cnic, roomNo, persons, beds, washroom }

Navigation:

- Activity: `ui.manager.activities.ManagerMainActivity`
- Graph: `res/navigation/manager_nav_graph.xml`

Technical notes:

- Coroutines + Tasks.await used in `ui.manager.data.ManagerRepository`
- ViewBinding enabled; layouts under `res/layout`
- To route managers after login, `LoginActivity` checks `managers/{uid}` and opens `ManagerMainActivity`

Build requirements:

- Android SDK configured (set ANDROID_HOME or local.properties sdk.dir)
- Google Services configured (existing `app/google-services.json`)