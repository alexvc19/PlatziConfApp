package com.example.platziconf.network

import com.example.platziconf.model.Conference
import com.example.platziconf.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val CONFERENCES_COLLECTION_NAME="conferenes"
const val SPEAKERS_COLLECTION_NAME="speakers"

class FirestoreService{
    val firebaseFirestore = FirebaseFirestore.getInstance()
    // clase para que los datos sean persistentes pedanser consultados offline
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {

        firebaseFirestore.firestoreSettings = settings
    }
    fun getSpeakers(callback: Callback<List<Speaker>>)
    {
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result ){
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}