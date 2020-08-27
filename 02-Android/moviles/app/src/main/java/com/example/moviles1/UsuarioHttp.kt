package com.example.moviles1

import java.util.*
import kotlin.collections.ArrayList

class UsuarioHttp(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var cadula: String,//cadula en vez de cedula.
    var nombre: String,
    var correo: String,
    var estadoCivil: String,
    var password: String,
    var pokemons: ArrayList<PokemonHttp>
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date
    init {
        fechaCreacion= Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}