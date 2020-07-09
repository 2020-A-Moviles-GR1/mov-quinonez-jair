import java.io.*
import kotlin.collections.ArrayList

fun main(args: Array<String>){
    var pais = Pais()
    var ciudad = Ciudad()
    println("CRUD con Archivos")
    while (true){
        opciones()
        println("Escoja una opcion: ")
        var opcion = readLine()!!.toInt()
        if(opcion == 1){
            pais.crear_pais()
            println("Ciudad Existente?: (1) Si o (2)No")
            var existe = readLine()!!.toInt()
            if (existe==2){
                ciudad.crear_ciudad()

            }else{
                println("Guardado")
            }


        }else if (opcion == 2){
            pais.leer_pais()



        }else if (opcion == 3){
            pais.editar_pais()

        }else if (opcion == 4){
            pais.eliminar_pais()

        }else if (opcion == 5){
            ciudad.crear_ciudad()

        }else if (opcion == 6){
            ciudad.leer_ciudad()

        }else if (opcion == 7){
            ciudad.editar_ciudad()


        }else if (opcion == 8){
            ciudad.eliminar_ciudad()

        }else if (opcion == 9){
            break

        }else{
            println("Opcion no valida, pulse una tecla ")
            var c = readLine()!!.toString()

        }

    }



}
fun opciones(){
    println("OPCIONES")
    println("******************************************")
    println("PAIS")
    println("1: Insertar\n2: Leer\n3: Actualizar\n4: Eliminar")
    println("******************************************")
    println("CIUDAD")
    println("5: Insertar\n6: Leer\n7: Actualizar\n8: Eliminar")
    println("**************************************************")
    println("9: Salir")

}

class Pais{
    var id_pais: Int =0
    var nombre: String =""
    var area: Double =0.0
    var costa: Boolean = false
    var ciudad: String = ""
    /*constructor(id_pais: Int, nombre: String, area: Double, costa: Boolean, ciudad: String){
        this.id_pais = id_pais
        this.nombre = nombre
        this.area = area
        this.costa = costa
        this.ciudad = ciudad
    }*/
    fun crear_pais(){
        val path = "./src/Pais.txt"
        println("ID")
        var id_pais = readLine()!!.toInt().toString()
        println("Nombre")
        var nombre = readLine()!!.toString()
        println("Area")
        var area = readLine()!!.toDouble().toString()
        println("Costa(true/false)")
        var costa = readLine()!!.toBoolean().toString()
        println("Ciudad")
        var ciudad = readLine()!!.toString()
        try{
            val file_writer = FileWriter(path, true)
            file_writer.write("$id_pais:$nombre:$area:$costa:$ciudad")
            println("Pais guardado")
            file_writer.close()


        }catch (ex: Exception){
            println("Error al crear"+ex)
        }




    }
    fun leer_pais(){
        val path = "./src/Pais.txt"
        //var salida = ""
        println("Se meustra: ")
        println("id:Nombre:Area:Costa:Ciudad")
        var array_paises = ArrayList<String>()
        File(path).useLines { lines -> lines.forEach { array_paises.add(it) } }
        array_paises.forEach { println(">"+it) }
        /*File(path).forEachLine { line -> array_paises.add(line) }
        array_paises.forEach {
            salida = salida + " "+it.split(":")[0]+
                it.split(":")[1]+it.split(":")[2]+
                it.split(":")[3]+it.split(":")[4] }
        println*/
    }
    fun editar_pais(){
        val path = "./src/Pais.txt"
        println("Ingrese el indice del pais: ")
        var indice=readLine()!!.toString()
        println("Asi se ve: "+indice)
        var array_pais = ArrayList<String>()
        File(path).forEachLine { line -> array_pais.add(line)}
        var indiceEditar = array_pais.indexOfFirst{ line->line.split(":")[0]== indice }

        println(indiceEditar)
        if (indiceEditar > -1){
            println("Nuevo ID")
            var id_pais = readLine()!!.toInt().toString()
            println("Nuevo Nombre")
            var nombre = readLine()!!.toString()
            println("Nueva Area")
            var area = readLine()!!.toDouble().toString()
            println("Nueva Costa(true/false)")
            var costa = readLine()!!.toBoolean().toString()
            println("Nueva Ciudad")
            var ciudad = readLine()!!.toString()
            array_pais[indiceEditar] = "$id_pais:$nombre:$area:$costa:$ciudad"


        }else{
            println("No se encontro el Indice")

        }
        try {
            val file_writer = FileWriter(path,true)
            File(path).writeText("")
            array_pais.forEach { file_writer.write(it+"\n") }
            println("Pais Editado.")
            file_writer.close()

        }catch (ex: Exception){
            println("No se pudo Editar el Pais.")

        }

    }
    fun eliminar_pais(){
        val path = "./src/Pais.txt"
        println("Ingrese el indice del pais: ")
        var indice=readLine()!!.toString()
        //println("Asi se ve: "+indice)
        var array_pais = ArrayList<String>()
        File(path).forEachLine { line -> array_pais.add(line)}
        var indice_borrar = array_pais.indexOfFirst{ line->line.split(":")[0]== indice }
        if(indice_borrar>-1){
            array_pais.removeAt(indice_borrar)

        }else{
            println("No se encontro el Pais")
        }
        try {
            val file_writer = FileWriter(path, true)
            File(path).writeText("")
            array_pais.forEach { file_writer.write(it+"\n") }
            println("Se elimino"+ indice)
            file_writer.close()
        }catch (ex: Exception){
            println("No se pudo eliminar")
        }
    }




}
class Ciudad{
    var id_ciudad: Int =0
    var nombre_ciudad: String =""
    var habitantes: Double =0.0
    var puerto: Boolean = false
    var alcalde: String = ""
    /*constructor(id_ciudad: Int, nombre_ciudad: String, habitantes: Double, puerto: Boolean, capital_pais: Boolean){
        this.id_pais = id_pais
        this.nombre = nombre
        this.area = area
        this.costa = costa
        this.ciudad = ciudad
    }*/
    fun crear_ciudad(){
        val path = "./src/Ciudad.txt"
        println("ID: ")
        var id_ciudad = readLine()!!.toInt().toString()
        println("Nombre: ")
        var nombre_ciudad = readLine()!!.toString()
        println("Habitantes: ")
        var habitantes = readLine()!!.toDouble().toString()
        println("Puerto(true/false): ")
        var puerto = readLine()!!.toBoolean().toString()
        println("Nombre del alcalde: ")
        var alcalde = readLine()!!.toString()
        try{
            val file_ciudad = FileWriter(path, true)
            file_ciudad.write("$id_ciudad:$nombre_ciudad:$habitantes:$puerto:$alcalde")
            println("Ciudad guardada")
            file_ciudad.close()


        }catch (ex: Exception){
            println("Error al crear"+ex)
        }




    }
    fun leer_ciudad(){
        val path = "./src/Ciudad.txt"
        //var salida = ""
        println("Se meustra: ")
        println("ID:Nombre:Habitantes:Puerto:Nombre alcalde")
        var array_paises = ArrayList<String>()
        File(path).useLines { lines -> lines.forEach { array_paises.add(it) } }
        array_paises.forEach { println(">"+it) }
        /*File(path).forEachLine { line -> array_paises.add(line) }
        array_paises.forEach {
            salida = salida + " "+it.split(":")[0]+
                it.split(":")[1]+it.split(":")[2]+
                it.split(":")[3]+it.split(":")[4] }
        println*/
    }
    fun editar_ciudad(){
        val path = "./src/Ciudad.txt"
        println("Ingrese el indice de la ciudad: ")
        var indice=readLine()!!.toString()
        //println("Asi se ve: "+indice)
        var array_pais = ArrayList<String>()
        File(path).forEachLine { line -> array_pais.add(line)}
        var indiceEditar = array_pais.indexOfFirst{ line->line.split(":")[0]== indice }

        //println(indiceEditar)
        if (indiceEditar > -1){
            println("Nuevo ID: ")
            var id_ciudad = readLine()!!.toInt().toString()
            println("Nuevo Nombre: ")
            var nombre_ciudad = readLine()!!.toString()
            println("Habitantes: ")
            var habitantes = readLine()!!.toDouble().toString()
            println("Nuevo Puerto(true/false): ")
            var puerto = readLine()!!.toBoolean().toString()
            println("Nuevo Nombre del alcalde: ")
            var alcalde = readLine()!!.toString()
            array_pais[indiceEditar] = "$id_ciudad:$nombre_ciudad:$habitantes:$puerto:$alcalde"


        }else{
            println("No se encontro el indice")

        }
        try {
            val file_ciudad = FileWriter(path,true)
            File(path).writeText("")
            array_pais.forEach { file_ciudad.write(it+"\n") }
            println("Pais Editado.")
            file_ciudad.close()

        }catch (ex: Exception){
            println("No se pudo Editar el Pais.")

        }

    }
    fun eliminar_ciudad(){
        val path = "./src/Ciudad.txt"
        println("Ingrese el indice de la ciudad: ")
        var indice=readLine()!!.toString()
        //println("Asi se ve: "+indice)
        var array_pais = ArrayList<String>()
        File(path).forEachLine { line -> array_pais.add(line)}
        var indice_borrar = array_pais.indexOfFirst{ line->line.split(":")[0]== indice }
        if(indice_borrar>-1){
            array_pais.removeAt(indice_borrar)

        }else{
            println("No se encontro el Pais")
        }
        try {
            val file_writer = FileWriter(path, true)
            File(path).writeText("")
            array_pais.forEach { file_writer.write(it+"\n") }
            println("Se elimino: "+ indice)
            file_writer.close()
        }catch (ex: Exception){
            println("No se pudo eliminar")
        }
    }




}






