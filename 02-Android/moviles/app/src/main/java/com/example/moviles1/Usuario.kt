package com.example.moviles1

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Usuario(var nombre: String?,
              var edad:Int,
              var fecha: Date,
              var sueldo: Double): Parcelable {
    constructor(parcel: Parcel) : this(
        //Deserializa Lee
        parcel.readString(),
        parcel.readInt(),
        parcel.readSerializable() as Date,
        parcel.readDouble()
    ) {
    }
    //Serializa Escribir
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeSerializable(fecha)
        parcel.writeDouble(sueldo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}