package com.example.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
//La clase coloca la informacion de la base de datos de firebase en nuestrosViews
class ScheduleAdapter(val scheduleListener: ScheduleListener):RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    var listConferene = ArrayList<Conference>()

    //archivos ue vamos a utlizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule,parent,false))

    //Elementos  que vamos a optener de la lista
    override fun getItemCount() = listConferene.size

    //funcion que coloca los datos en la pocision que necesitamos
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConferene[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDataformat = SimpleDateFormat("HH:mm")
        val simpleDateformatAMPM= SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDataformat.format(conference.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateformatAMPM.format(conference.datetime).toUpperCase()

            holder.itemView.setOnClickListener{
                scheduleListener.onConferenceClicked(conference, position )
            }


    }
    //funcion que permite ingresar datos a nuestro adaptador
    fun updateData(data: List<Conference>){
        listConferene.clear()
        listConferene.addAll(data)
        notifyDataSetChanged()
    }

    //Referencia de los elementos visuales de los xml
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvScheduleNameItem)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvSchedulenamespeakeritem)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvTagItem)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvTimeConferenceitem)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvAmitem)

    }

}