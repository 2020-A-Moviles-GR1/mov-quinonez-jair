package com.example.exam1_qui_paci

class PaisHttp (
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombrePais: String,
    var area: String,
    var costa: String,
    var ciudad: String
) {
    override fun toString(): String {
        return "Nombre: ${nombrePais}, Area: ${area}, Costa: ${costa}, Ciudad: ${ciudad}"
    }

}