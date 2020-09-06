package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_vista_ciudades.*
import com.github.kittinunf.result.Result
import java.util.ArrayList

class VistaCiudades : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.3:1337"
    var arreglotest = mutableListOf<String>()
    var list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_ciudades)
        arreglotest.add("PRUEBA ARREGLO")
        //obtenerCiudad()
        Log.i("http-get","Como esta aqui ${arreglotest}")
        ServicioBDD.obtenerCiudad()

        val lista_ciudades = ServicioBDD.list_ciudadesHttp
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
        //obtenerCiudad()
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


