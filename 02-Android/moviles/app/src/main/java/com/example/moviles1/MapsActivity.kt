package com.example.moviles1

import android.content.pm.PackageManager
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener {

    private lateinit var mMap: GoogleMap
    var tiene_permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisos()
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
        //1) Permisos
        solicitarConfiguracion(mMap)
        establecerListeners(mMap)
        val casa = LatLng(0.914865, -79.688789)
        val titulo = "CERCA-CASA"
        val zoom = 17f
        anadiMarcador(casa, titulo)
        moverCamara(casa,zoom)
        val poliLineaUno = googleMap.addPolyline(
            PolylineOptions().clickable(true).add(
                LatLng(0.922003, -79.683733),
                LatLng(0.911665, -79.685703),
                LatLng(0.915125, -79.691540),
                LatLng(0.919115, -79.688206)
            )
        )
        val poligonoUno = googleMap.addPolygon(
            PolygonOptions().clickable(true).add(
                LatLng(0.916144, -79.690954),
                LatLng(0.916980, -79.686840),
                LatLng(0.912723, -79.686493),
                LatLng(0.912101, -79.690570)
            )
        )
        poligonoUno.fillColor = -0xc771c4
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(casa))
        /*Puentos
        * 0.922003, -79.683733
        * 0.911665, -79.685703
        * 0.915125, -79.691540
        * 0.919115, -79.688206
        *
        *0.916144, -79.690954
        * 0.916980, -79.686840
        * 0.912723, -79.686493
        * 0.912101, -79.690570
        *
        * */

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }
    fun moverCamara(latLng: LatLng, zoom: Float = 10f ){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))

    }
    fun solicitarConfiguracion(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                Log.i("mapa", "Tiene permisos FINE LOCATION")
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true

        }
    }
    fun anadiMarcador(latLng: LatLng, title: String){
        mMap.addMarker(
            MarkerOptions().position(latLng).title(title)
        )

    }
    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION)
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos){
            Log.i("mapa", "Tiene permisos FINE LOCATION")
            this.tiene_permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this, //Contexto
            arrayOf(
              android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
                1 //codigo que esperamos
            )
        }
    }
    fun establecerListeners(map: GoogleMap){
        with(map){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)

        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("mapa","Empezo a mover onCameraMoveStarted")
    }

    override fun onCameraMove() {
        Log.i("mapa","Moviendo onCameraMove")
    }

    override fun onCameraIdle() {
        Log.i("mapa","Quieto onCameraIdle")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("mapa","PolyLinea ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa","Polygono ${p0.toString()}")
    }
}