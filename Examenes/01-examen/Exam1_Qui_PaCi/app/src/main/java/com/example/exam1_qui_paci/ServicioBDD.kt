package com.example.exam1_qui_paci

class ServicioBDD {
    companion object{
        var list_pais = arrayListOf<PaisC>()

        fun addPais(pais: PaisC){
            list_pais.add(pais)
        }
        fun deletePais(pais: PaisC){
            list_pais.remove(pais)
        }
        fun editPais(posi: Int, pais: PaisC){
            list_pais.set(posi,pais)
        }
        fun getPais(posi: Int):PaisC{
            return list_pais.get(posi)
        }





        var list_ciudad = arrayListOf<CiudadC>()
        fun addCiudad(ciudad: CiudadC){
            list_ciudad.add(ciudad)
        }
        fun deleteCiudad(ciudad: CiudadC){
            list_ciudad.remove(ciudad)
        }
        fun editPais(posi: Int, ciudad: CiudadC){
            list_ciudad.set(posi,ciudad)
        }
        fun getCiudad(posi: Int):CiudadC{
            return list_ciudad.get(posi)
        }

    }
}