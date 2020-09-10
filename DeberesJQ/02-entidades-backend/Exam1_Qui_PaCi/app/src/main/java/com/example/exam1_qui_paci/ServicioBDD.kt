package com.example.exam1_qui_paci

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class ServicioBDD {
    //val list = mutableListOf<String>()
    val urlPrincipal = "http://192.168.1.3:1337"

    companion object {
        val urlPrincipal = "http://192.168.1.3:1337"
        var list_ciudadesHttp = mutableListOf<String>()
        var list_paisesHttp = mutableListOf<String>()
        var list_pais = arrayListOf<PaisC>()

        fun obtenerPais() {

            //var list = arrayListOf<String>()
            val url = urlPrincipal + "/pais"

            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-get", "Datos: ${data}")
                        val paises = Klaxon().parseArray<PaisHttp>(data)
                        if (paises != null) {
                            list_ciudadesHttp.clear()
                            paises.forEach {
                                Log.i("http-get", "Datos: ${it.nombrePais}")
                                val thing =
                                    it.nombrePais + " " + it.area + " " + it.costa + " " + it.ciudad
                                //arreglotest.add(thing)

                                list_paisesHttp.add(thing)

                                Log.i("http-get", "TEST ${list_ciudadesHttp}")

                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-get", "Error: ${ex.message}")
                    }

                }


            }
            //Log.i("http-get", "Est mierda: ${list}")
            //return list

        }

        fun crearPaisHttp(pais: PaisHttp) {
            /*"var id: Int,\n" +
                    "    var createdAt: Long,\n" +
                    "    var updatedAt: Long,\n" +
                    "    var nombreCiudad: String,\n" +
                    "    var habitantes: String,\n" +
                    "    var puerto: String,\n" +
                    "    var alcalde: String"*/

            var url = urlPrincipal + "/pais"
            val parametrosPais = listOf(
                "nombrePais" to pais.nombrePais,
                "area" to pais.area,
                "costa" to pais.costa,
                "ciudad" to pais.ciudad
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
            }
        }

        fun updatePaisHttp(identificador: String, pais: PaisHttp) {
            val url = urlPrincipal + "/pais/" + identificador
            Log.i("http-get", "Este es el identificador ${url}")
            //val instant: Instant = releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            val parametrosPaisHttp = listOf(
                "nombrePais" to pais.nombrePais,
                "area" to pais.area,
                "costa" to pais.costa,
                "ciudad" to pais.ciudad
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
                            val usuarioString = result.get()
                            Log.i("http-get", " ESTE ES EL EDITADO ${usuarioString}")
                        }
                    }
                }
        }

        fun deletePaisHttp(identificador: String) {
            val url = urlPrincipal + "/pais/" + identificador
            url.httpDelete()
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                        }
                        is Result.Success -> {
                            val usuarioString = result.get()
                            Log.i("http-get","Se elimino")
                        }
                    }
                }
        }

        fun addPais(pais: PaisC) {
            list_pais.add(pais)
        }

        fun deletePais(pais: PaisC) {
            list_pais.remove(pais)
        }

        fun editPais(posi: Int, pais: PaisC) {
            list_pais.set(posi, pais)
        }

        fun getPais(posi: Int): PaisC {
            return list_pais.get(posi)
        }


        var list_ciudad = arrayListOf<CiudadC>()
        fun addCiudad(ciudad: CiudadC) {
            list_ciudad.add(ciudad)
        }

        fun deleteCiudad(ciudad: CiudadC) {
            list_ciudad.remove(ciudad)
        }

        fun editPais(posi: Int, ciudad: CiudadC) {
            list_ciudad.set(posi, ciudad)
        }

        fun getCiudad(posi: Int): CiudadC {
            return list_ciudad.get(posi)
        }

        fun obtenerCiudad() {

            //var list = arrayListOf<String>()
            val url = urlPrincipal + "/ciudad"

            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-get", "Datos: ${data}")
                        val ciudades = Klaxon().parseArray<CiudadHttp>(data)
                        if (ciudades != null) {
                            list_ciudadesHttp.clear()
                            ciudades.forEach {
                                Log.i("http-get", "Datos: ${it.nombreCiudad}")
                                val thing =
                                    it.nombreCiudad + " " + it.habitantes + " " + it.puerto + " " + it.alcalde
                                //arreglotest.add(thing)

                                list_ciudadesHttp.add(thing)

                                Log.i("http-get", "TEST ${list_ciudadesHttp}")

                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-get", "Error: ${ex.message}")
                    }

                }


            }
            //Log.i("http-get", "Est mierda: ${list}")
            //return list

        }

        fun crearCiudad(ciudad: CiudadHttp) {
            /*"var id: Int,\n" +
                    "    var createdAt: Long,\n" +
                    "    var updatedAt: Long,\n" +
                    "    var nombreCiudad: String,\n" +
                    "    var habitantes: String,\n" +
                    "    var puerto: String,\n" +
                    "    var alcalde: String"*/

            var url = urlPrincipal + "/ciudad"
            val parametrosCiudad = listOf(
                "nombreCiudad" to ciudad.nombreCiudad,
                "habitantes" to ciudad.habitantes,
                "puerto" to ciudad.puerto,
                "alcalde" to ciudad.alcalde
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
            }
        }

        fun updateCiudadHttp(identificador: String, ciudad: CiudadHttp) {
            val url = urlPrincipal + "/ciudad/" + identificador
            Log.i("http-get", "Este es el identificador ${url}")
            //val instant: Instant = releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            val parametrosCiudadHttp = listOf(
                "nombreCiudad" to ciudad.nombreCiudad,
                "habitantes" to ciudad.habitantes,
                "puerto" to ciudad.puerto,
                "alcalde" to ciudad.alcalde
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
                }
        }

        fun deleteCiudadHttp(identificador: String) {
            val url = urlPrincipal + "/ciudad/" + identificador
            url.httpDelete()
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                        }
                        is Result.Success -> {
                            val usuarioString = result.get()
                            Log.i("http-get","Se elimino")
                        }
                    }
                }
        }


    }
}