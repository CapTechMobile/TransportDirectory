package com.captechventures.transportdirectory.viewmodels

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.captechventures.transportdirectory.data.Car
import com.captechventures.transportdirectory.data.CarRepository

/**
 * The ViewModel for [CarListFragment].
 */
class CarListViewModel internal constructor(
        private val carRepository: CarRepository
) : ViewModel() {

    private val shouldShowFavorites = MutableLiveData<Boolean>()
    private val carList = MediatorLiveData<List<Car>>()

    init {

        shouldShowFavorites.value = false

        val liveFavCarList = Transformations.switchMap(shouldShowFavorites) {
            if (it) {
                carRepository.getFavoriteCars()
            } else {
                carRepository.getCars()
            }

        }

        carList.addSource(liveFavCarList, carList::setValue)
    }

    fun toggleFavorite() {
        shouldShowFavorites.value = !shouldShowFavorites()
    }

    fun shouldShowFavorites() = shouldShowFavorites.value == true

    fun getCars() = carList

}
