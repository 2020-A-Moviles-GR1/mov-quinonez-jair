package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pais.*

class Pais : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais)
        btn_verPais.setOnClickListener {
            boton -> irPaisesList()
        }


    }
    fun irPaisesList(){
        val ir = Intent(this, VistaPaises::class.java)
        startActivity(ir)
    }

}