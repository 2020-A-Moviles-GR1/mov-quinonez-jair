package com.example.moviles1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*

class IntentEnviaParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)
        val numero_encontrado = intent.getIntExtra(
            "numero", 0)
        if(numero_encontrado != 0){
            Log.i("intents", "El numero encontrado es ${numero_encontrado}")
        }
        btn_devolver_respuesta.setOnClickListener {
            finish()//para terminar la actividad
        }
        val textoCompartido = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (textoCompartido != null){
            Log.i("intent", "El texto es ${textoCompartido}")
        }
    }

}