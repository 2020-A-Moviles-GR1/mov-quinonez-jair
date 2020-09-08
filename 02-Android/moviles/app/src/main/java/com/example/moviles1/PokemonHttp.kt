package com.example.moviles1

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*

class PokemonHttp(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombre: String,
    var usuario: Int
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date
    init {
        fechaCreacion= Date(createdAt)
        fechaActualizacion= Date(updatedAt)
    }



}