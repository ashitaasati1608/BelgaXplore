package com.ashita.belgaxplore.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ashita.belgaxplore.domain.data.Locations
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object BelgaXploreApiService {

    val db = FirebaseFirestore.getInstance()

    suspend fun getCountryLocationsList(locations: SnapshotStateList<Locations>): MutableList<Locations> {
        var data = mutableListOf<Locations>()
        println("Service function got called")
        db.collection("locations")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    data.add(document.toObject(Locations::class.java))
                }
            }.addOnFailureListener {
                println("gafla ${it.stackTrace}")
            }.addOnCompleteListener {
                data = it.result.toObjects(Locations::class.java)
            }.await()
        return data
    }

    suspend fun getLocationDetailsById(id: String): Locations? {
        var locationsDto: Locations? = null
        db.collection("locations").get().addOnSuccessListener {
            locationsDto = it.toObjects(Locations::class.java).firstOrNull { it.id == id }
        }.addOnCanceledListener {
            locationsDto = null
        }.await()
        return locationsDto
    }

    private fun <T> SnapshotStateList<T>.updateList(list: List<T>) {
        clear()
        addAll(list)

    }
}
