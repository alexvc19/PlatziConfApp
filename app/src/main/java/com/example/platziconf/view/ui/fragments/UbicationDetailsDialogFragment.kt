package com.example.platziconf.view.ui.fragments


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.example.platziconf.R
import com.example.platziconf.model.Ubication
import kotlinx.android.synthetic.main.fragment_ubication_details_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class UbicationDetailsDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication_details_dialog, container, false)
    }
        //funcion que coloca en nuestros views la informacion del objeto Ubicacion
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)
        toolbarUbication.setTitleTextColor(Color.WHITE)
        toolbarUbication.setNavigationOnClickListener{
            dismiss()
        }
        val ubicacion = Ubication()
        toolbarUbication.title = ubicacion.name

        tvDetailConferenceHour.text = ubicacion.name
        tvAdress.text = ubicacion.address
        tvPhone.text = ubicacion.phone
        tvWebsite.text = ubicacion.website

        //evento click que al pulsarlo dispara un intent que nos lleva a una aplicacion externa
        llTelefono.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubicacion.phone}")
            }
            startActivity(intent)
        }
        llWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubicacion.website)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
