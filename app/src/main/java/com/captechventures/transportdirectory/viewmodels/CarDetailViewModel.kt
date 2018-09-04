package com.captechventures.transportdirectory.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.captechventures.transportdirectory.data.Car
import com.captechventures.transportdirectory.data.CarRepository

/**
 * The ViewModel used in [CarDetailFragment].
 */
class CarDetailViewModel(
        private val carRepository: CarRepository,
        private val vin: String
) : ViewModel() {

    val car: LiveData<Car>

    init {
        car = carRepository.getCarByVin(vin)
    }

    fun updateCarFavoriteFlag() {

        val car = car.value
        if (car != null) {
            carRepository.updateCarFavoriteFlag(vin, car.favorite)
        }

    }

    fun isCarFavorited(): Boolean {

        val car = car.value
        if (car != null) {
            return car.favorite
        }
        return false
    }
}
