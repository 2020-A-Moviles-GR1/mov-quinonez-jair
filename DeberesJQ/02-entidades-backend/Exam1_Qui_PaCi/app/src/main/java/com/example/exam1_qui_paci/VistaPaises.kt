package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_vista_paises.*
import kotlin.math.log

class VistaPaises : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_vista_paises)
        val lista_paises = ServicioBDD.list_paisesHttp

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_paises
        )
        list_paises.adapter = adaptador
        list_paises.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.i("lista", "Posi ${lista_paises[position]}")
            escogerPais(position)

            //Log.i("lista", "LA p $position")
        }


    }
    fun escogerPais(sitio: Int){
        var intent = Intent(
            this,
            Pais::class.java

        )
        intent.putExtra("posicion", sitio)
        startActivity(intent)

    }
}
