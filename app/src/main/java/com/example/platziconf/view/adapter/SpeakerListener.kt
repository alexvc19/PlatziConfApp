package com.example.platziconf.view.adapter


import com.example.platziconf.model.Speaker
import java.text.FieldPosition

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}
