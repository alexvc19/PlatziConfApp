package com.example.platziconf.view.ui.fragments


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController

import com.example.platziconf.R
import com.example.platziconf.model.Ubication
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    //funcion que coloca el mapa en el fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

    }

    //funcion para manipular el mapa
    override fun onMapReady(googleMap: GoogleMap) {
        //codigo para hacer zoom en el mapa con las cordenadas que definimos
        val ubication = Ubication()
        val zoom = 16f
        val centerMap = LatLng(ubication.latitude, ubication.longgitude)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        //codigo para crear el marcador del lugar exacto de las cordenadas
        val centerMark = LatLng(ubication.latitude, ubication.longgitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title("Platzi Conf 2019")

        //agregar una imagen personalizada al marcador de la ubicacion
        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi_conf) }as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,200,200,false)

        //agregamos al mapa la imagen
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
            googleMap?.addMarker(markerOptions)
            googleMap?.setOnMarkerClickListener(this)

            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))
    }

    //funcion click que muestra el detalle de la ubicacion
    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }


}
