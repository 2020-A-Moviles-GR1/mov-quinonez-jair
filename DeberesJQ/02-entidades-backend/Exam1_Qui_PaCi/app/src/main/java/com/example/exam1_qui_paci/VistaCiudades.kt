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
import com.squareup.picasso.Picasso
import java.util.ArrayList

class VistaCiudades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_ciudades)
        val lista_ciudades = ServicioBDD.list_ciudadesHttp
        Log.i("test", "LA CIUDAD${lista_ciudades}")
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_ciudades
        )
        list_ciudades.adapter = adaptador
        list_ciudades.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.i("lista", "Posi ${lista_ciudades[position]}")
            //escogerCiudad(lista_ciudades[position].id)
            var img = lista_ciudades[position].url_img
            Log.i("test", "iimg ${img}")
            Picasso.get().load(lista_ciudades[position].url_img).into(iv_ciudad)
            var temporal = lista_ciudades[position].nombreCiudad
            txt_ID.setText(temporal)
            btn_irEditar.setOnClickListener {
                    boton -> escogerCiudad(lista_ciudades[position].id)
            }
            btn_mapear.setOnClickListener{
                var ciudad : CiudadHttp? = ServicioBDD.getCiudad(lista_ciudades[position].id)

                irMaps(ciudad!!.id)
                Log.i("test","EL ID EN EL PUT ${ciudad!!.id}")
            }

        }




    }
    init {
        ServicioBDD.obtenerCiudad()

    }
    fun escogerCiudad(sitio: Int){
        var intent = Intent(
            this,
            Ciudad::class.java
        )
        intent.putExtra("posicion", sitio)
        startActivity(intent)
    }
    fun irMaps(sitio: Int){

        var intent = Intent(this, MapsCiudad::class.java)
        intent.putExtra("ciudad", sitio)
        startActivity(intent)
    }


}


