package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //val list = mutableListOf<String>()
    val urlPrincipal = "http://192.168.1.3:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //obtenerCiudad()
        btn_irciudad.setOnClickListener{
            boton -> irCiudad()
        }
        btn_irpais.setOnClickListener{
            boton -> irPais()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity", "OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity", "OnRestart")
    }
    fun irCiudad(){
        val intentExplicito = Intent(this, Ciudad::class.java)
        startActivity(intentExplicito)
    }
    fun irPais(){
        val intentExplicito = Intent(this, Pais::class.java)
        startActivity(intentExplicito)
    }

}