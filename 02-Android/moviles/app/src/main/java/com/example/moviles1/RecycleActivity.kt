package com.example.moviles1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycle.*

class RecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)
    val listaUsusarios = arrayListOf<UsuarioHttp>()
        listaUsusarios.add(
            UsuarioHttp(
                1,
                123123123,
                123123123,
                "0800002452",
                "Jair",
                "j@email.com",
                "Soltero",
                "Super34ura",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsusarios.add(
            UsuarioHttp(
                2,
                123123123,
                123123123,
                "1234562452",
                "Pepe",
                "P@email.com",
                "Soltero",
                "Super34ura",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsusarios.add(
            UsuarioHttp(
                3,
                123123123,
                123123123,
                "6543212452",
                "Carlos",
                "carl@email.com",
                "Soltero",
                "Super34ura",
                arrayListOf<PokemonHttp>()
            )
        )
        iniciarRecyclerView(
            listaUsusarios,
            this,
            rv_usuarios
        )

    }
    fun iniciarRecyclerView(
        lista: List<UsuarioHttp>,
        actividad: RecycleActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadirUsusario = RecyclerAdaptador(
            lista,
            actividad,
            recycler_view
        )
        rv_usuarios.adapter = adaptadirUsusario
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadirUsusario.notifyDataSetChanged()

    }
}