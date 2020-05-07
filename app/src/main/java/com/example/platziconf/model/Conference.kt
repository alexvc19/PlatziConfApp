package com.example.platziconf.model

import java.io.Serializable
import java.util.*

// : serilizble le damos el soporte que podamos pasar este objeto entre activitys

class Conference: Serializable {
    lateinit var title: String
    lateinit var description: String
    lateinit var tag: String
    lateinit var datetime: Date
    lateinit var speaker: String
}