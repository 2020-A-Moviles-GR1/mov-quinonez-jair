package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ciudad.*

class Ciudad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)
        var indice = intent.getIntExtra("posicion", -1)
        Log.i("test", "El id es ${indice}")
        if(indice>-1){
            txPrincipal.setText("EDITAR ó ELIMINAR")
            btn_guardarCiudad.setText("EDITAR CIUDAD")
            var ciudad: CiudadC = ServicioBDD.getCiudad(indice)
            text_nombreCiudad.setText(ciudad.nombre_ciudad)
            text_habitaciones.setText(ciudad.num_habitantes.toString())
            text_puerto.setText(ciudad.puerto.toString())
            text_alcalde.setText(ciudad.alcalde)

            btn_guardarCiudad.setOnClickListener {
                ServicioBDD.editPais(indice, CiudadC(
                    text_nombreCiudad.text.toString(),
                    text_habitaciones.text.toString().toDouble(),
                    text_puerto.text.toString().toBoolean(),
                    text_alcalde.text.toString()
                ))
                Toast.makeText(applicationContext, "Ciudad Editada", Toast.LENGTH_SHORT).show()
                irCiudadLIst()

            }
        }else {
            btn_guardarCiudad.setText("GUARDAR")
            btn_guardarCiudad.setOnClickListener{
                ServicioBDD.addCiudad(
                    CiudadC(
                        text_nombreCiudad.text.toString(),
                        text_habitaciones.text.toString().toDouble(),
                        text_puerto.text.toString().toBoolean(),
                        text_alcalde.text.toString()

                    )
                )
                Toast.makeText(applicationContext, "Ciudad Guardada",Toast.LENGTH_SHORT).show()
                //irCiudadLIst()
                limpiarCiudad()
            }
        }
        btn_deleteC.setOnClickListener {
            var ciudad: CiudadC = ServicioBDD.getCiudad(indice)
            ServicioBDD.deleteCiudad(ciudad)
            Toast.makeText(applicationContext, "Ciudad Eliminada",Toast.LENGTH_SHORT).show()
            irCiudadLIst()
        }

        btn_verCiudad.setOnClickListener {
            boton -> irCiudadLIst()
        }

    }
    fun irCiudadLIst(){
        val ir = Intent(this, VistaCiudades::class.java)
        startActivity(ir)
    }
    fun irCiudad(){
        var ir = Intent(
            this,
            Ciudad::class.java
        )
        startActivity(ir)
    }
    fun limpiarCiudad(){
        text_nombreCiudad.setText("")
        text_habitaciones.setText("")
        text_puerto.setText("")
        text_alcalde.setText("")
    }

}
class CiudadC(var nombre_ciudad: String, var num_habitantes: Double, var puerto: Boolean, var alcalde: String){
    override fun toString(): String {
        return "Nombre: ${nombre_ciudad} Habitantes: ${num_habitantes} ¿Puerto?: ${puerto} Nombre Alcalde: ${alcalde}"
    }

}