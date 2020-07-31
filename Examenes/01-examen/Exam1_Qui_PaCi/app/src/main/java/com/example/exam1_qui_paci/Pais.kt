package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_pais.*


class Pais : AppCompatActivity() {
    companion object datosPais{
        var bddPais = ArrayList<PaisC>()
        fun crear(){
            val pais = PaisC(
                id = 0,
                nombrePais = "Peru",
                area = 12.0,
                costa = false,
                ciudad = "PAris"
            )
            bddPais.add(pais)
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais)
        btn_verPais.setOnClickListener {
            boton -> irPaisesList()
        }
        btn_guardarPais.setOnClickListener {
            boton -> addPais()
        }


    }
    fun irPaisesList(){
        val ir = Intent(this, VistaPaises::class.java)
        startActivity(ir)
    }
    fun addPais(){
        var id = text_idPais.text.toString().toInt()
        var nombrePais = text_nombrePais.text.toString()
        var area = text_area.text.toString().toDouble()
        var costa = text_costa.text.toString().toBoolean()
        var ciudad = text_ciudad.text.toString()
        val pais = PaisC(id,nombrePais,area,costa,ciudad)
        bddPais.add(pais)

        Log.i("insert","${pais} Se capturo")
    }


}
class PaisC(var id: Int, var nombrePais: String, var area: Double, var costa: Boolean, var ciudad: String) {
    override fun toString(): String {
        return "${this.id} ${this.nombrePais} ${this.area} ${this.costa} ${this.ciudad}"
    }
}