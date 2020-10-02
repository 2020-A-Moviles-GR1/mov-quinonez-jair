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
        var indice = intent.getIntExtra("posicion", -1)
        Log.i("test", "El ide es ${indice}")



        if (indice > -1) {
            var pais: PaisHttp? = ServicioBDD.getPais(indice)
            /*Log.i("test", "El ide DENTRO del IF ${indice}")
            Log.i("test", "El PAIS ${pais}")*/
            t_Titulo.setText("EDITAR ó ELIMINAR")
            btn_guardarPais.setText("EDITAR PAIS")
            if (pais != null){
                Log.i("test", "El PAIS ${pais.nombrePais}")
                text_nombrePais.setText(pais.nombrePais)
                text_area.setText(pais.area)
                text_costa.setText(pais.costa)
                text_ciudad.setText(pais.ciudad)
            }
            btn_guardarPais.setOnClickListener {
                Log.i("test", "INICIO EL UPDATE")
                ServicioBDD.editPais(indice,
                    text_nombrePais.text.toString(),
                    text_area.text.toString(),
                    text_costa.text.toString(),
                    text_ciudad.text.toString()

                )
                Log.i("test", "ACABO")
                Toast.makeText(applicationContext, "Pais Editado", Toast.LENGTH_SHORT).show()
                limpiarPais()
                irPaisesList()

            }

        } else {
            btn_guardarPais.setText("GUARDAR")
            btn_guardarPais.setOnClickListener {
                ServicioBDD.addPais(
                    text_nombrePais.text.toString(),
                    text_area.text.toString(),
                    text_costa.text.toString(),
                    text_ciudad.text.toString()

                )
                Toast.makeText(applicationContext, "PAis Guardado", Toast.LENGTH_SHORT).show()
                //irPaisesList()
                limpiarPais()
            }
        }
        btn_eliminar.setOnClickListener {
            var pais: PaisHttp? = ServicioBDD.getPais(indice)
            ServicioBDD.eliminarPais(pais!!.id)
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

