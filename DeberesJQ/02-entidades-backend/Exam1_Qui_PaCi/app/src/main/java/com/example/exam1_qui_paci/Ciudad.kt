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
        ServicioBDD.obtenerCiudad()
        var indice = intent.getIntExtra("posicion", -1)
        Log.i("test", "El id es ${indice}")
        //var indiceBack: CiudadHttp
        if(indice >0){
            txPrincipal.setText("EDITAR ó ELIMINAR")
            btn_guardarCiudad.setText("EDITAR CIUDAD")
            /*var ciudad: CiudadHttp
            txt_id.setText(ciudad.id)
            text_nombreCiudad.setText(ciudad.nombreCiudad.toString())
            text_habitaciones.setText(ciudad.habitantes.toString())
            text_puerto.setText(ciudad.puerto.toString())
            text_alcalde.setText(ciudad.alcalde.toString())*/
            btn_guardarCiudad.setOnClickListener {
                Log.i("test", "INICIO EL UPDATE")
                ServicioBDD.updateExpansion(indice, CiudadHttp(
                        text_nombreCiudad.text.toString(),
                        text_habitaciones.text.toString().toDouble().toString(),
                        text_puerto.text.toString().toBoolean().toString(),
                        text_alcalde.text.toString()
                ))
                Log.i("test", "ACABO")
                Toast.makeText(applicationContext, "Ciudad Editada", Toast.LENGTH_SHORT).show()
                //irCiudadLIst()

            }
        }else {
            btn_guardarCiudad.setText("GUARDAR")
            btn_guardarCiudad.setOnClickListener{
                ServicioBDD.crearCiudad(
                        CiudadHttp(text_nombreCiudad.text.toString(),
                            text_habitaciones.text.toString().toString(),
                            text_puerto.text.toString().toString(),
                            text_alcalde.text.toString())

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