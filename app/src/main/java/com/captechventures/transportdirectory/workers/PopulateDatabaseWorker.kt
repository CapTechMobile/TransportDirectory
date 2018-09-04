package com.captechventures.transportdirectory.workers

import android.util.Log
import androidx.work.Worker
import com.captechventures.transportdirectory.data.Car
import com.captechventures.transportdirectory.data.TransportDatabase
import com.captechventures.transportdirectory.utilities.CAR_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class PopulateDatabaseWorker : Worker() {
    private val TAG = PopulateDatabaseWorker::class.java.simpleName

    override fun doWork(): Worker.Result {
        val carType = object : TypeToken<List<Car>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            //Read in JSON of Cars
            val inputStream = applicationContext.assets.open(CAR_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            //Parse JSON to Car objects
            val cars: List<Car> = Gson().fromJson(jsonReader, carType)
            //Get an instance of the database for insertion
            val database = TransportDatabase.getInstance(applicationContext)
            //Insert all car objects into the Room database
            database.carDao().insertAll(cars)
            Worker.Result.SUCCESS
        } catch (ex: Exception) {
            Log.e(TAG, "Error prepopulating database with cars", ex)
            Worker.Result.FAILURE
        } finally {
            jsonReader?.close()
        }

    }
}