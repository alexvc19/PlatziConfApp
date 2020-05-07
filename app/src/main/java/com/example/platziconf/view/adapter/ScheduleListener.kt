package com.example.platziconf.view.adapter

import com.example.platziconf.model.Conference


interface ScheduleListener{

    fun onConferenceClicked(conference: Conference, position: Int){

    }
}