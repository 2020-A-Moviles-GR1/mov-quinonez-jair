package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_ciudad.*
import kotlinx.android.synthetic.main.activity_pais.*


class Pais : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais)
        ServicioBDD.obtenerPais()
        var indice = intent.getIntExtra("posicion", -1)
        Log.i("test", "El ide es ${indice}")
        if (indice > -1) {
            t_Titulo.setText("EDITAR ó ELIMINAR")
            btn_guardarPais.setText("EDITAR PAIS")
            btn_guardarPais.setOnClickListener {
                Log.i("test", "INICIO EL UPDATE")
                ServicioBDD.updatePaisHttp(
                    txt_id_pais.text.toString(),
                    PaisHttp(
                        text_nombrePais.text.toString(),
                        text_area.text.toString(),
                        text_costa.text.toString(),
                        text_ciudad.text.toString()
                    )
                )
                Log.i("test", "ACABO")
                Toast.makeText(applicationContext, "Pais Editada", Toast.LENGTH_SHORT).show()
                irPaisesList()

            }

        } else {
            btn_guardarPais.setText("GUARDAR")
            btn_guardarPais.setOnClickListener {
                ServicioBDD.crearPaisHttp(
                    PaisHttp(
                        text_nombrePais.text.toString(),
                        text_area.text.toString(),
                        text_costa.text.toString(),
                        text_ciudad.text.toString()
                    )
                )
                Toast.makeText(applicationContext, "PAis Guardado", Toast.LENGTH_SHORT).show()
                //irPaisesList()
                limpiarPais()
            }
        }
        btn_eliminar.setOnClickListener {
            //var pais: PaisC = ServicioBDD.getPais(indice)
            var ident = txt_id_pais.text.toString()
            ServicioBDD.deletePaisHttp(ident)
            Toast.makeText(applicationContext, "Eliminado", Toast.LENGTH_SHORT).show()
            //irPaisesList()

        }

        btn_verPais.setOnClickListener { boton ->
            irPaisesList()
        }


    }

    fun irPaisesList() {
        val ir = Intent(this, VistaPaises::class.java)
        startActivity(ir)
    }

    fun irPais(posi: Int) {
        val ir = Intent(
            this,
            Pais::class.java
        )
        ir.putExtra("posicion", posi)
        startActivity(ir)

    }

    fun limpiarPais() {
        text_nombrePais.setText("")
        text_area.setText("")
        text_costa.setText("")
        text_ciudad.setText("")
    }
}

class PaisC(var nombrePais: String, var area: Double, var costa: Boolean, var ciudad: String) {
    override fun toString(): String {
        return "Nombre: ${this.nombrePais} Area: ${this.area} Costa: ${this.costa} Ciudad: ${this.ciudad}"
    }
}