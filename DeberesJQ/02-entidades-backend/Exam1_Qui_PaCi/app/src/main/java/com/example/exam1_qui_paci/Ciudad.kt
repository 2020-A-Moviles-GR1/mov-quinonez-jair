package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ciudad.*
import kotlinx.android.synthetic.main.activity_vista_ciudades.*

class Ciudad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)
        var indice = intent.getIntExtra("posicion", -1)
        Log.i("test", "El id es ${indice}")
        if (indice > -1) {
            var ciudad: CiudadHttp? = ServicioBDD.getCiudad(indice)
            txPrincipal.setText("EDITAR ó ELIMINAR")
            btn_guardarCiudad.setText("EDITAR CIUDAD")
            if (ciudad != null){
                Log.i("test", "CIUDAD: ${ciudad.nombreCiudad}")
                text_nombreCiudad.setText(ciudad.nombreCiudad)
                text_habitaciones.setText(ciudad.habitantes)
                text_puerto.setText(ciudad.puerto)
                text_alcalde.setText(ciudad.alcalde)
                text_latitud.setText(ciudad.latitud)
                text_longitud.setText(ciudad.longitud)
                text_direccion.setText(ciudad.direccion_url)
                text_imagen.setText(ciudad.url_img)
            }
            btn_guardarCiudad.setOnClickListener {
                Log.i("test", "INICIO EL UPDATE")
                ServicioBDD.editCiudad(
                    indice,
                    text_nombreCiudad.text.toString(),
                    text_habitaciones.text.toString(),
                    text_puerto.text.toString(),
                    text_alcalde.text.toString(),
                    text_latitud.text.toString(),
                    text_longitud.text.toString(),
                    text_imagen.text.toString(),
                    text_direccion.text.toString()

                )
                Log.i("test", "ACABO")
                Toast.makeText(applicationContext, "Ciudad Editada", Toast.LENGTH_SHORT).show()
                irCiudadLIst()

            }
        } else {
            btn_guardarCiudad.setText("GUARDAR")
            btn_guardarCiudad.setOnClickListener {
                ServicioBDD.addCiudad(
                    text_nombreCiudad.text.toString(),
                    text_habitaciones.text.toString(),
                    text_puerto.text.toString(),
                    text_alcalde.text.toString(),
                    text_latitud.text.toString(),
                    text_longitud.text.toString(),
                    text_imagen.text.toString(),
                    text_direccion.text.toString()

                )

                Toast.makeText(applicationContext, "Ciudad Guardada", Toast.LENGTH_SHORT).show()
                limpiarCiudad()
            }
        }

        btn_deleteC.setOnClickListener {
            var ciudad : CiudadHttp? = ServicioBDD.getCiudad(indice)
            ServicioBDD.eliminarCiudad(ciudad!!.id)
            Toast.makeText(applicationContext, "Ciudad Eliminada", Toast.LENGTH_SHORT).show()
            limpiarCiudad()
        }
        btn_verCiudad.setOnClickListener { boton ->
            irCiudadLIst()

        }


    }

    fun irCiudadLIst() {
        val ir = Intent(this, VistaCiudades::class.java)
        startActivity(ir)
    }

    fun irCiudad() {
        var ir = Intent(
            this,
            Ciudad::class.java
        )
        startActivity(ir)
    }

    fun limpiarCiudad() {
        text_nombreCiudad.setText("")
        text_habitaciones.setText("")
        text_puerto.setText("")
        text_alcalde.setText("")
    }


}
