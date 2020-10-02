package com.example.exam1_qui_paci

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ServicioBDD {
    val urlPrincipal = "http://192.168.1.3:1337"

    companion object {
        val urlPrincipal = "http://192.168.1.4:1337"
        var list_ciudadesHttp = listOf<CiudadHttp>()
        var list_paisesHttp = listOf<PaisHttp>()
        var pais: PaisHttp? = null
        var ciudad: CiudadHttp? = null

        fun obtenerPais() {
            val url = urlPrincipal + "/pais"
            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-get", "Datos: ${data}")
                        val paises = Klaxon().parseArray<PaisHttp>(data)
                        if (paises != null) {
                            this.list_paisesHttp = paises
                            paises.forEach {
                                Log.i("http-get", "Datos: ${it.nombrePais}")
                                val thing =
                                    it.nombrePais + " " + it.area + " " + it.costa + " " + it.ciudad
                                Log.i("http-get", "TEST ${list_paisesHttp}")

                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-get", "Error: ${ex.message}")
                    }
                }
            }.join()
        }

        fun obtenerUnPais(id: Int) {
            val url = urlPrincipal + "/pais/${id}"
            Log.i("test", "El URL es ${url}")

            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-get", "Datos: ${data}")
                        val pais = Klaxon().parse<PaisHttp>(data)
                        if (pais != null) {
                            this.pais = pais
                            Log.i("test", "Nombre: ${pais.nombrePais} Area: ${pais.area}")
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-get", "Error: ${ex.message}")
                    }
                }
            }.join()
        }

        fun crearPaisHttp(nombreP: String, areaP: String, costaP: String, ciudadP: String) {
            var url = urlPrincipal + "/pais"
            val parametrosPais = listOf(
                "nombrePais" to nombreP,
                "area" to areaP,
                "costa" to costaP,
                "ciudad" to ciudadP
            )
            url.httpPost(parametrosPais).responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-get", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val pasiString = result.get()
                        Log.i("http-get", "${pasiString}")
                    }
                }
            }.join()
        }

        fun updatePaisHttp(
            id: Int,
            nombreP: String,
            areaP: String,
            costaP: String,
            ciudadP: String
        ) {
            val url = urlPrincipal + "/pais/${id}"
            Log.i("http-get", "Este es el identificador ${url}")
            //val instant: Instant = releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            val parametrosPaisHttp = listOf(
                "nombrePais" to nombreP,
                "area" to areaP,
                "costa" to costaP,
                "ciudad" to ciudadP
            )
            Log.i("http-klaxon", "Parametros: ${parametrosPaisHttp}")
            url.httpPut(parametrosPaisHttp)
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                            Log.i("http-klaxon", "Error put: ${error}")
                        }
                        is Result.Success -> {
                            val paisString = result.get()
                            Log.i("http-get", " ESTE ES EL EDITADO ${paisString}")
                        }
                    }
                }.join()
        }

        fun deletePaisHttp(id: Int) {
            val url = urlPrincipal + "/pais/${id}"
            url.httpDelete()
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                        }
                        is Result.Success -> {
                            val paisString = result.get()
                            Log.i("http-get", "Se elimino ${paisString}")
                        }
                    }
                }.join()
        }

        fun obtenerCiudad() {
            val url = urlPrincipal + "/ciudad"
            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-get", "Datos: ${data}")
                        val ciudades = Klaxon().parseArray<CiudadHttp>(data)
                        if (ciudades != null) {
                            this.list_ciudadesHttp = ciudades
                            ciudades.forEach {
                                Log.i("http-get", "Datos: ${it.nombreCiudad}")
                                val thing =
                                    it.nombreCiudad + " " + it.habitantes + " " + it.puerto + " " + it.alcalde
                                Log.i("http-get", "TEST ${list_ciudadesHttp}")
                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-get", "Error: ${ex.message}")
                    }
                }
            }.join()
        }

        fun obtenerUnaCiudad(id: Int) {
            val url = urlPrincipal + "/ciudad/${id}"
            Log.i("test", "El URL es ${url}")

            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-get", "Datos: ${data}")
                        val ciudad = Klaxon().parse<CiudadHttp>(data)
                        if (ciudad != null) {
                            this.ciudad = ciudad
                            Log.i(
                                "test",
                                "Nombre: ${ciudad.nombreCiudad} Habitates: ${ciudad.habitantes}"
                            )
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-get", "Error: ${ex.message}")
                    }
                }
            }.join()
        }

        fun crearCiudadHttp(
            nombreC: String,
            habitantes: String,
            puerto: String,
            alcalde: String,
            latitud: String,
            longitud: String,
            url_img: String,
            direccion: String
        ) {

            var url = urlPrincipal + "/ciudad"
            val parametrosCiudad = listOf(
                "nombreCiudad" to nombreC,
                "habitantes" to habitantes,
                "puerto" to puerto,
                "alcalde" to alcalde,
                "latitud" to latitud,
                "longitud" to longitud,
                "url_img" to url_img,
                "direccion_url" to direccion
            )
            url.httpPost(parametrosCiudad).responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-get", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val ciudadString = result.get()
                        Log.i("http-get", "${ciudadString}")
                    }
                }
            }.join()
        }

        fun updateCiudadHttp(
            id: Int,
            nombreC: String,
            habitantes: String,
            puerto: String,
            alcalde: String,
            latitud: String,
            longitud: String,
            url_img: String,
            direccion: String
        ) {
            val url = urlPrincipal + "/ciudad/${id}"
            Log.i("http-get", "Este es el identificador ${url}")
            //val instant: Instant = releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            val parametrosCiudadHttp = listOf(
                "nombreCiudad" to nombreC,
                "habitantes" to habitantes,
                "puerto" to puerto,
                "alcalde" to alcalde,
                "latitud" to latitud,
                "longitud" to longitud,
                "url_img" to url_img,
                "direccion_url" to direccion
            )
            Log.i("http-klaxon", "Parametros: ${parametrosCiudadHttp}")
            url.httpPut(parametrosCiudadHttp)
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                            Log.i("http-klaxon", "Error put: ${error}")
                        }
                        is Result.Success -> {
                            val usuarioString = result.get()
                            Log.i("http-get", " ESTE ES EL EDITADO ${usuarioString}")
                        }
                    }
                }.join()
        }

        fun deleteCiudadHttp(id: Int) {
            val url = urlPrincipal + "/ciudad/${id}"
            url.httpDelete()
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                        }
                        is Result.Success -> {
                            val usuarioString = result.get()
                            Log.i("http-get", "Se elimino")
                        }
                    }
                }.join()
        }

        fun addPais(nombreP: String, areaP: String, costaP: String, ciudadP: String) {
            crearPaisHttp(nombreP, areaP, costaP, ciudadP)
            obtenerPais()

        }

        fun editPais(id: Int, nombreP: String, areaP: String, costaP: String, ciudadP: String) {
            updatePaisHttp(id, nombreP, areaP, costaP, ciudadP)
            obtenerUnPais(id)
        }

        fun eliminarPais(id: Int) {
            deletePaisHttp(id)
            obtenerUnPais(id)

        }

        fun getPais(pos: Int): PaisHttp? {
            obtenerUnPais(pos)
            return pais

        }

        /****************CIUDAD*****************/
        fun addCiudad(
            nombreC: String,
            habitantes: String,
            puerto: String,
            alcalde: String,
            latitud: String,
            longitud: String,
            url_img: String,
            direccion: String
        ) {
            crearCiudadHttp(
                nombreC,
                habitantes,
                puerto,
                alcalde,
                latitud,
                longitud,
                url_img,
                direccion
            )
            obtenerCiudad()
        }

        fun editCiudad(
            id: Int,
            nombreC: String,
            habitantes: String,
            puerto: String,
            alcalde: String,
            latitud: String,
            longitud: String,
            url_img: String,
            direccion: String
        ) {
            updateCiudadHttp(
                id,
                nombreC,
                habitantes,
                puerto,
                alcalde,
                latitud,
                longitud,
                url_img,
                direccion
            )
            obtenerUnaCiudad(id)
        }

        fun eliminarCiudad(id: Int) {
            deleteCiudadHttp(id)
            obtenerUnaCiudad(id)
        }

        fun getCiudad(pos: Int): CiudadHttp? {
            obtenerUnaCiudad(pos)
            return ciudad
        }


    }
}