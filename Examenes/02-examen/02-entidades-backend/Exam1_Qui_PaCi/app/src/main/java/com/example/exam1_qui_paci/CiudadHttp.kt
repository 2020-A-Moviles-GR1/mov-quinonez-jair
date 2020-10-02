package com.example.exam1_qui_paci

class CiudadHttp(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombreCiudad: String,
    var habitantes: String,
    var puerto: String,
    var alcalde: String,
    var latitud: String,
    var longitud: String,
    var url_img: String,
    var direccion_url: String
) {
    override fun toString(): String {
        return "NombreCiudad: ${nombreCiudad} Habitantes: ${habitantes} " +
                "Puerto: ${puerto} Alcalde: ${alcalde} "
    }
    //Latitud: ${latitud} Longitud: ${longitud} Url_Img:${url_img} Direccio: ${direccion}

}