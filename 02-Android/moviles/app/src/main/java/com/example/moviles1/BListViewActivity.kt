package com.example.moviles1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_view.*

class BListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)
        val listEntrenador = arrayListOf<Entrenador>()
        listEntrenador.add(Entrenador("Test","Testing"))
        listEntrenador.add(Entrenador("Test2","Testing2"))
        listEntrenador.add(Entrenador("Test3","Testing3"))
        listEntrenador.add(Entrenador("Test4","Testing4"))
        listEntrenador.add(Entrenador("Test5","Testing5"))

        val adactador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //nombre layaout
            listEntrenador //Lista
        )
        lv_numeros.adapter = adactador
        lv_numeros.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id -> Log.i("List-View", "Position $position")


        }
        btn_anadir_entrenador.setOnClickListener {
            anadirEntrenador(
                adactador,listEntrenador
            )

        }



    }
    fun anadirEntrenador(
        adactador: ArrayAdapter<Entrenador>,
        listEntrenador: ArrayList<Entrenador>
    ){
        listEntrenador.add(Entrenador("AGregado","Agregado"))
        adactador.notifyDataSetChanged()

    }
}