package com.example.clasesdesis145

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.clasesdesis145.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var dbHandler:BaseDatos?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Obtenemos los datos de la base de datos
        val dbHandler = BaseDatos(this)
        val listTasks: List<Lugares> = dbHandler.lugar

        // Iteramos sobre la lista de lugares y añadimos un marcador para cada uno
        for (lugares in listTasks) {
            Log.d("Datos", "--->" + lugares.nombre)

            // Asegúrate de que las coordenadas sean válidas antes de agregar el marcador
            if (lugares.latitud != null && lugares.longitud != null) {
                val position = LatLng(lugares.latitud, lugares.longitud)
                mMap.addMarker(MarkerOptions().position(position).title(lugares.nombre))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 0f))
            } else {
                Log.d("Datos", "Latitud o Longitud no válidos para " + lugares.nombre)
            }
        }
    }

}
