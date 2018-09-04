package com.captechventures.transportdirectory.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * The Data Access Object for the Car class.
 */
@Dao
interface CarDao {

    @Query("SELECT * FROM cars ORDER BY manufacturer, model")
    fun getCars(): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE vin = :vin")
    fun getCarByVin(vin: String): LiveData<Car>

    @Query("SELECT * FROM cars WHERE favorite = 1 ORDER BY manufacturer, model")
    fun getCarsByFavorite(): LiveData<List<Car>>

    @Query("UPDATE cars SET favorite = :boolInt WHERE vin = :vin")
    fun updateCarFavoriteFlag(vin: String, boolInt : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cars: List<Car>)

    //Extra Unused Examples of Queries that can be constructed in a DAO.
    @Query("SELECT * FROM cars WHERE manufacturer = :manufacturer ORDER BY model")
    fun getCarsByManufacturer(manufacturer: String): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE model = :model ORDER BY color")
    fun getCarsByModel(model: String): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE modelYear = :modelYear ORDER BY manufacturer, model")
    fun getCarsByModelYear(modelYear: Int): LiveData<List<Car>>
}
