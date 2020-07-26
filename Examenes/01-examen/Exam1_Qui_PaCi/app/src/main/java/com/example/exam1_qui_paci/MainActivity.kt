package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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