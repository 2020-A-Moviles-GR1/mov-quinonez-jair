package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_ciudad.*
import kotlinx.android.synthetic.main.activity_vista_ciudades.*

class VistaCiudades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_ciudades)
        val lista_ciudades = ServicioBDD.list_ciudad
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_ciudades
        )
        list_ciudades.adapter = adaptador
        list_ciudades.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.i("lista", "Posi ${lista_ciudades[position]}")
            escogerCiudad(position)


        }


    }
    fun escogerCiudad(sitio: Int){
        var intent = Intent(
            this,
            Ciudad::class.java
        )
        intent.putExtra("posicion", sitio)
        startActivity(intent)
    }


}