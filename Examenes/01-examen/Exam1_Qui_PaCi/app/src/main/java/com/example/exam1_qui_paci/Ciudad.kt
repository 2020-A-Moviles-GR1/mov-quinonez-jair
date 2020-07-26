package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ciudad.*

class Ciudad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)
        btn_verCiudad.setOnClickListener {
            boton -> irCiudadLIst()
        }

    }
    fun irCiudadLIst(){
        val ir = Intent(this, VistaCiudades::class.java)
        startActivity(ir)
    }

}