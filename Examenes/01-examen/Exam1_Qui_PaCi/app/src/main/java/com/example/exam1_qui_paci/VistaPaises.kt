package com.example.exam1_qui_paci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_vista_paises.*

class VistaPaises : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_paises)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Pais.bddPais
        )
        list_paises.adapter = adaptador
        list_paises.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> Log.i("lista", "LA p $position") }
        }
    }
