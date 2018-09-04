package com.captechventures.transportdirectory.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.captechventures.transportdirectory.data.CarRepository

/**
 * Factory for creating a [CarDetailViewModel] with a constructor that takes a [TransportRepository]
 * and vin for the current [Car].
 */
class CarDetailViewModelFactory(
        private val carRepository: CarRepository,
        private val vin: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarDetailViewModel(carRepository, vin) as T
    }
}
