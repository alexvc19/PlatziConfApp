package com.example.platziconf.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.platziconf.R
import com.example.platziconf.view.adapter.ScheduleAdapter
import com.example.platziconf.view.adapter.ScheduleListener
import com.example.platziconf.viewmodel.ScheduleViewModel
import com.example.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule.*


/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener{
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //herecia de la clase viewmodel
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)

       //refresh obtiene los datos de firebase
        viewModel.refresh()

        //instancia del adaptador donde vamos a colocar nuestra informacion
        //el adaptador es nuestro recyclearview
        scheduleAdapter = ScheduleAdapter(this)

        //le agregamos algunos atributos al recyclerview
        rvSchedule.apply{
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }

        observeViewModel()

    }
    //funcion para observar constantemente la informacion del recyclerview
    //constantemente pregunta si hay unos cambios o evento de la patalla siempre los muestre
    fun observeViewModel() {
        viewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)
        })

        //codigo hace que al cargar la informacion el progresBar se haga invisible
        viewModel.isLoading.observe(this, Observer<Boolean> {
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    //evento click que dispara el vendo para ir al fragmentDialog
    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }

}
