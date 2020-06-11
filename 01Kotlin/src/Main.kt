import java.util.*
fun main(args:Array<String>){
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
    if(sueldo ==12.20){

    }else{

    }
    //nuestro switch en kotlin
    when(sueldo){
        12.20 -> print("Sueldo Normal")
        -12.20 -> print("sueldo Negativo")
        else -> printlin("no se reconoce el sueldo")

    }
    val esSueldoMayorEstabecido = if (sueldo == 12.20) true else false
    //expresion ? X : V
    calcularSueldo(1000.00,14.00)
    calcularSueldo(tasa = 16.00, sueldo = 800.00) //parametros nombrados, se pueden mandar en el orden que se quiera.

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