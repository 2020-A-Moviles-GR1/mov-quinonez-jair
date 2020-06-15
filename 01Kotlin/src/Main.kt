import java.util.*
import kotlin.collections.ArrayList
import java.util.function.Consumer

fun main(args: Array<String>){
    print("HOLA")

    //Ejemplo Java
    // Int edad = 12;
    //Variables mutables
    //variables inmutables
    //Siempre vamos a preferir las inmutables
    //variables descriptivas
    var edadProfesor = 31 //no se especifica el tipo de dato, Punto y coma no es necesario
    //duck typing
    var edadCachorro:Int
    edadCachorro = 3
    //inmutables
    val numeroCuenta = 123456 //no se pueen reasignar, tipo de variable preferida.
    //Tipos de variable.
    val nombreProfesor="Vicente Adrian"
    val sueldo = 12.20
    val apellidoProfesor = 'a'
    val fechaNacimietno = Date() //New Date
    /*if(sueldo ==12.20){
    }else{
    }*/
    //nuestro switch en kotlin
    when(sueldo){
        12.20 -> print("Sueldo Normal")
        -12.20 -> print("sueldo Negativo")
        else -> println("no se reconoce el sueldo")

    }
    //val esSueldoMayorEstabecido = if (sueldo == 12.20) true else false
    //expresion ? X : V
    calcularSueldo(1000.00,14.00)
    calcularSueldo(tasa = 16.00, sueldo = 800.00) //parametros nombrados, se pueden mandar en el orden que se quiera.
   //Arreglos
    val arregloConstante: Array<Int> = arrayOf(1, 2, 3)
    val arregloCumpleanos: ArrayList<Int> = arrayListOf(30, 31, 22, 23, 20)
    print(arregloCumpleanos)
    arregloCumpleanos.add(24)
    print(arregloCumpleanos)
    // arregloCumpleanos.remove(30)
    arregloCumpleanos.remove(30)
    print(arregloCumpleanos)
    arregloCumpleanos
            .forEach { valorIteracion: Int ->
                println("Valor iteracion: " + valorIteracion)
            }
    arregloCumpleanos
            .forEach(
                    { valorIteracion: Int ->
                        println("Valor iteracion: " + valorIteracion)
                    }
            )

    // Operadores -> TODOS LOS LENGUAJES
    // ForEach no devuelve nada -> Unit
    arregloCumpleanos
            .forEach { iteracion: Int ->
                println("Valor de la iteracion " + iteracion)
                println("Valor con -1 = ${iteracion * -1} ")
            }

    val respuestaArregloForEach = arregloCumpleanos
            .forEachIndexed { index: Int, iteracion: Int ->
                println("Valor de la iteracion " + iteracion)
            }
    println(respuestaArregloForEach) // Void Unit

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados
    val respuestaMap = arregloCumpleanos
            .map { iterador: Int ->
                iterador * -1
            }
    val respuestaMapDos= arregloCumpleanos
            .map { iterador: Int ->
                val nuevoValor = iterador * -1
                val otroValor = nuevoValor * 2
                return@map Date()
            }
    println(respuestaMap)
    println(respuestaMapDos)
    println(arregloCumpleanos)

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo que cumpla esa expresion
    val respuestaFilter = arregloCumpleanos
            .filter {
                iteracion:Int ->
                val esMayorA23 = iteracion > 23
                return@filter esMayorA23
            }
    arregloCumpleanos
            .filter {
                iteracion:Int -> iteracion > 23
            }
    println(respuestaFilter)
    println(arregloCumpleanos)
}
//funciones
fun calcularSueldo(sueldo:Double, tasa:Double = 12.00, calculoEspecial: Int? = null/*calculo Especial puede ser nulo */):Double {
    if (calculoEspecial !=null){
        return sueldo * tasa * calculoEspecial
    }else{
        return sueldo * tasa
    }


}
fun imprimirMensaje():Unit { //unit = void
    println("")
}
