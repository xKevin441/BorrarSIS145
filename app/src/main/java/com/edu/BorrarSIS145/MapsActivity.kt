package com.edu.BorrarSIS145

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.edu.BorrarSIS145.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var dbHandler: BaseDatos? = null
    var listTasks: List<Lugares> = ArrayList<Lugares>()

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

        dbHandler = BaseDatos(this)
        listTasks = dbHandler?.lugar ?: emptyList()

        for(lugares in listTasks){
            val lugar = LatLng(lugares.latitud.toDouble(), lugares.longitud.toDouble())
            mMap.addMarker(MarkerOptions().position(lugar).title(lugares.descripcion))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar))
        }

       /*val Sucre = LatLng(-19.034750, -65.266647)
        mMap.addMarker(MarkerOptions().position(Sucre).title("Ciudad de Sucre"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Sucre))

        val Cochabamba = LatLng(-17.403839, -66.170288)
        mMap.addMarker(MarkerOptions().position(Cochabamba).title("Ciudad de Cochabamba"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Cochabamba))

        val LaPaz = LatLng(-16.489689, -68.119293)
        mMap.addMarker(MarkerOptions().position(LaPaz).title("Ciudad de La Paz"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LaPaz))

        val Potosi = LatLng(-19.572281, -65.755005)
        mMap.addMarker(MarkerOptions().position(Potosi).title("Ciudad de Potosi"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Potosi))

        val SantaCruz = LatLng(-17.78629, -63.18117)
        mMap.addMarker(MarkerOptions().position(SantaCruz).title("Santa Cruz de la sierra"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SantaCruz))

        val Tarija = LatLng(-21.53549, -64.72956)
        mMap.addMarker(MarkerOptions().position(Tarija).title("Ciudad de Tarija"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Tarija))

        val Oruro = LatLng(-17.98333, -67.15)
        mMap.addMarker(MarkerOptions().position(Oruro).title("Ciudad de Oruro"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Oruro))

        val Pando = LatLng(-11.0267100, -68.7691800)
        mMap.addMarker(MarkerOptions().position(Pando).title("Ciudad de Pando"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pando))

        val Trinidad = LatLng(-14.84, -64.90)
        mMap.addMarker(MarkerOptions().position(Trinidad).title("Ciudad de Trinidad"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Trinidad))

        val Uyuni = LatLng(-20.45967, -66.82503)
        mMap.addMarker(MarkerOptions().position(Uyuni).title("Uyuni"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Uyuni))*/
    }
}