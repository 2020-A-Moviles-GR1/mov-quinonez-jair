package com.example.moviles1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_http.*
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.3:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener.setOnClickListener {
            obtenerUsusarios()
        }
        btn_crear.setOnClickListener {
            crearUsuario()
        }
    }

    fun crearUsuario() {
        val url = urlPrincipal + "/Usuario"
        //"cadula": "11111562888888",
        //"nombre": "Jair Andres",
        //"correo": "jair@email.com",
        //"estadoCivil": "Soltero",
        //"password": "Super34gura"
        val parametroUsuario: List<Pair<String, String>> = listOf(
            "cadula" to "1122334455",
            "nombre" to "Pepe Test",
            "correo" to "pepe@email.com",
            "estadoCivil" to "Casado",
            "password" to "A12345689b"
        )
        url.httpPost(parametroUsuario).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("http-klaxon", "Error: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("http-klaxon", "${usuarioString}")
                }
            }
        }
    }

    fun obtenerUsusarios() {
        val pokemonString = """
            {
            "createdAt": 1597811937529,
            "updatedAt": 1597811937529,
            "id": 1,
            "nombre": "Pikachu Nuevo AS",
            "usuario": 1
          }
        """.trimIndent()
        val pokemonInstancia = Klaxon().parse<PokemonHttp>(pokemonString)
        if (pokemonInstancia != null) {
            Log.i(
                "http-klaxon",
                "Nombre: ${pokemonInstancia.nombre}"
            )
            Log.i(
                "http-klaxon",
                "Fecha Creacion: ${pokemonInstancia.fechaCreacion}"
            )
        }

        val url = urlPrincipal + "/Usuario"
        url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data:${data}")

                    val usuarios = Klaxon()
                        .parseArray<UsuarioHttp>(data)
                   /* if (usuarios != null) {
                        usuarios.forEach {
                            Log.i(
                                "http-klaxon",
                                "Nombre: ${it.nombre}" + " Estado Civil: ${it.estadoCivil}"
                            )
                            if (it.pokemons.size > 0) {
                                it.pokemons.forEach {
                                    Log.i("http-klaxon", " Nombre: ${it.nombre}")
                                }
                            }
                        }
                    }*/
                    //DEBER KLAXON
                    if(usuarios != null){
                        usuarios.forEach{
                            Log.i(
                                "http-klaxon",
                                "\nNombre_Usuario: ${it.nombre}\n"
                            )
                            if(it.pokemons is List<*>){
                                if(it.pokemons!!.size > 0){
                                    it.pokemons!!.forEach{
                                        it as PokemonHttp
                                        Log.i(
                                            "http-klaxon",
                                            "id_Usuario: ${it.usuario} ------- Nombre_Pokemon: ${it.nombre}\n"
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Log.i("http-klaxon","\n\nCONSULTAR POKEMONS\n\n")
                    obtenerPokemons()
                    //DEBER KLAXON
                }

                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http-Klaxon", "Error: ${ex.message}")

                }
            }

        }
    }
    //DEBER KLAXON
    fun obtenerPokemons() {
        val url = urlPrincipal + "/pokemon"
        url
            .httpGet()
            .responseString{
                    request, response, result ->
                when(result){
                    is Result.Success ->{
                        val data = result.get()
                        val pokemons = Klaxon()
                            .converter(PokemonHttp.myConverter)
                            .parseArray<PokemonHttp>(data)
                        if(pokemons != null){
                            pokemons.forEach{
                                Log.i("http-klaxon", "POKEMON: ${it.nombre}" + ", USUARIO: ${it.usuario}\n"
                                )
                            }
                        }
                    }
                    is Result.Failure ->{
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.message}")
                    }
                }
            }
    }
//DEBER KLAXON
}

