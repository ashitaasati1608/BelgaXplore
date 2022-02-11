package com.ashita.belgaxplore.data

import com.ashita.belgaxplore.di.AppModule.provideFirebaseInstance
import com.ashita.belgaxplore.domain.data.Locations
import kotlinx.coroutines.tasks.await

object BelgaXploreApiService {

    suspend fun getCountryLocationsList(): MutableList<Locations> {
        var data = mutableListOf<Locations>()
        provideFirebaseInstance().collection("locations")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    data.add(document.toObject(Locations::class.java))
                }
            }.addOnFailureListener {
                //ToDo : Return Error
            }.addOnCompleteListener {
                data = it.result.toObjects(Locations::class.java)
            }.await()

        return data
    }

    suspend fun getLocationDetailsById(id: String): Locations? {
        var locationsDto: Locations? = null
        provideFirebaseInstance().collection("locations").get().addOnSuccessListener {
            locationsDto = it.toObjects(Locations::class.java).firstOrNull { it.id == id }
        }.addOnCanceledListener {
            locationsDto = null
        }.await()
        return locationsDto
    }
}
