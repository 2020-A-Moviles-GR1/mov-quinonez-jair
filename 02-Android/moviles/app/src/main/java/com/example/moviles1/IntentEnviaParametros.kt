package com.example.moviles1

import android.app.Activity
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
        val micha = intent.getParcelableExtra<Mascota>("mascota")
        if (micha != null){
            Log.i("parcelable", "${micha.nombre}  ${micha.duenio?.nombre}")
        }
        val arregloMascota = intent.getParcelableArrayListExtra<Mascota>("arregloMascotas")
        if (arregloMascota != null){
            arregloMascota.forEach{
                if(it != null){
                    Log.i("parcelable", "EN ARREGLO")
                    Log.i("parcelable", "${it.nombre}  ${it.duenio?.nombre}")
                }

            }
        }

        btn_devolver_respuesta.setOnClickListener {
            finish()//para terminar la actividad
        }
        btn_resp_aceptar.setOnClickListener {
            val nombre = "Jair"
            val edad = 23
            val intentRespuesta = Intent()
            intentRespuesta.putExtra("nombre", nombre)
            intentRespuesta.putExtra("edad", edad)
            setResult(
                RESULT_OK,
                intentRespuesta

            )
            finish()
        }
        btn_resp_cancelar.setOnClickListener {
            var intentCancelado = Intent()
            setResult(
                RESULT_CANCELED,
                intentCancelado
            )
            finish()
        }
        val textoCompartido = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (textoCompartido != null){
            Log.i("intent", "El texto es ${textoCompartido}")
        }
    }

}