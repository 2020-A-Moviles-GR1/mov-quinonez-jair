package com.example.exam1_qui_paci

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.net.URL

class MapsCiudad : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false
    var ciudades_mapa = ArrayList<CiudadHttp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_ciudad)
        ServicioBDD.obtenerCiudad()
        solicitarPermisos()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoom = 17f
        establecerConfiguracionMapa(mMap)
        mMap.setOnInfoWindowClickListener(this)
        var ciudad_extra_int = intent.getIntExtra("ciudad", -1)
        var ciudad_ext: CiudadHttp? = ServicioBDD.getCiudad(ciudad_extra_int)
        if (ciudad_ext != null) {
            Log.i("test", "ciu_extra ${ciudad_ext}")
            var bit = getBitmap(ciudad_ext.url_img)
            if (bit != null) {
                agregarMarcador(
                    LatLng(ciudad_ext.latitud.toDouble(), ciudad_ext.longitud.toDouble()),
                    ciudad_ext.nombreCiudad,
                    ciudad_ext.direccion_url,
                    ciudad_ext.url_img
                )
                moverCamaraZoom(
                    LatLng(
                        ciudad_ext.latitud.toDouble(),
                        ciudad_ext.longitud.toDouble()
                    )
                )
            }
        }


    }

    override fun onStart() {
        super.onStart()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var ciudad_extra_int = intent.getIntExtra("ciudad", -1)
        var ciudad_ext: CiudadHttp? = ServicioBDD.getCiudad(ciudad_extra_int)
        if (ciudad_ext != null) {
            Log.i("test", "ciu_extra ${ciudad_ext}")
        }
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisos = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val estaPermitido = permisos == PackageManager.PERMISSION_GRANTED
            if (estaPermitido) {
                mapa.isMyLocationEnabled = true;
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun getBitmap(url: String): BitmapDescriptor {
        var bmp = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
        bmp = Bitmap.createScaledBitmap(bmp, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(bmp)
    }

    fun solicitarPermisos() {
        val permisos = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val estaPermitido = permisos == PackageManager.PERMISSION_GRANTED
        if (estaPermitido) {
            this.tienePermisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1
            )

        }
    }

    fun agregarMarcador(latLng: LatLng, titulo: String, url_redirect: String, urlImg: String) {
        // Log.i("MapsActivity",urlImg)

        mMap.addMarker(
            MarkerOptions().position(latLng).title(titulo).snippet(url_redirect)
                .icon(getBitmap(urlImg))

        )
    }

    fun moverCamaraZoom(latLng: LatLng, zoom: Float = 10f) {
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )

    }

    override fun onInfoWindowClick(p0: Marker?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(p0?.snippet)) //p0?.snippet
        startActivity(browserIntent)
    }
}