package com.example.moviles1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVida : AppCompatActivity() {
    var numeroActual = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("Activity", "OnCreate")
        Log.i("Activity", ServicioBDDMemoria.numero.toString())
        numeroActual = ServicioBDDMemoria.numero
        if (numeroActual != 0){
            tv_numero.text = numeroActual.toString()
        }
        btn_anadir.setOnClickListener { sumarValor() }
    }
    fun sumarValor(){
        numeroActual = numeroActual + 1
        ServicioBDDMemoria.anadir_numero()
        tv_numero.text = numeroActual.toString()
    }
    override fun onStart() {
        super.onStart()
        Log.i("Activity", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity", "OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity", "OnRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Activity","OnSaveInstance")
        outState?.run {
            putInt("numeroGuardadoActual", numeroActual)

        }
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Activity","OnRestoreInstance")
        val valor_recuperado = savedInstanceState?.getInt("numeroGuardadoActual")
        if (valor_recuperado != null){
            this.numeroActual = valor_recuperado
            tv_numero.text = this.numeroActual.toString()
        }
    }

}