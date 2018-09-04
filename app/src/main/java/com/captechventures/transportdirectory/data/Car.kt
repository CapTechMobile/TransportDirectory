package com.captechventures.transportdirectory.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
        @PrimaryKey @ColumnInfo(name = "vin") val vin: String,
        override val manufacturer: String,
        override val model: String,
        override val modelYear: Int,
        val color: String,
        val image: String,
        val price: String,
        val trim: String,
        val description: String,
        var favorite: Boolean = false
) : Vehicle() {

    override fun toString() = String.format("%s %s %s %s", modelYear, manufacturer, model, trim)
}
