package com.example.moviles1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity", "OnCreate")
        btn_ciclo_vida.setOnClickListener {boton ->
            irCicloDeVida()

        }
        btn_list_view.setOnClickListener { boton ->
            irListView()
        }
        btn_intent_respuesta.setOnClickListener { boton ->
            irIntentEnviar()
        }
        btn_intent_implicito.setOnClickListener {
            boton -> enviarIntentConRespuesta()
        }
        btn_resp_propia.setOnClickListener {
            boton -> enviarIntentRespPropia()
        }
        btn_http.setOnClickListener {
            boton -> irHttp()
        }
        btn_recycler.setOnClickListener {
            boton -> irRecycler()
        }

    }
    fun enviarIntentRespPropia(){
        val intentExplicito=Intent(
            this,
            IntentEnviaParametros::class.java
        )
        startActivityForResult(intentExplicito, 305)
    }
    fun irIntentEnviar(){
        val intentExplicito=Intent(
            this,
            IntentEnviaParametros::class.java
        )
        intentExplicito.putExtra("numero",2)
        val jair = Usuario(
            "Jair",
            31,
            Date(),
            1.0
        )
        val micha = Mascota("micha", jair)
        val arregloMascotas = arrayListOf<Mascota>(micha)


        intentExplicito.putExtra("mascota", micha)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)
        startActivity(intentExplicito)

    }
    fun enviarIntentConRespuesta(){
        var intenConRespesta = Intent(Intent.ACTION_PICK,
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        startActivityForResult(intenConRespesta, 305)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            RESULT_OK -> {
                when(requestCode){
                    304 -> {
                        val uri = data?.data
                        if (uri != null) {
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado", "Telefono: ${telefono}")
                        }
                    }
                    305 -> {
                        if (data != null){
                            val nombre = data.getStringExtra("nombre")
                            val edad = data.getIntExtra("edad", 0)
                            Log.i("resultado","Nombre : ${nombre}, Edad :${edad}" )
                        }
                    }
                }
                Log.i("resultado","OK")
            }
            Activity.RESULT_CANCELED -> {
                Log.i("resultado", "damned")
            }

        }
    }
    fun irCicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java

        )
        //this.startActionMode()
        startActivity(intentExplicito)
    }
    fun irListView(){
        val intentExplicito = Intent(
            this,
            BListViewActivity::class.java

        )
        //this.startActionMode()
        startActivity(intentExplicito)
    }
    fun irHttp(){
        val intent = Intent(
            this,
            HttpActivity::class.java
        )
        startActivity(intent)
    }
    fun irRecycler(){
        val intet = Intent(this,
        RecycleActivity::class.java)
        startActivity(intet)
    }

}