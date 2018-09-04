package com.captechventures.transportdirectory.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.captechventures.transportdirectory.data.CarRepository

/**
 * Factory for creating a [CarListViewModel] with a constructor that takes a [CarRepository].
 */
class CarListViewModelFactory(
        private val repository: CarRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = CarListViewModel(repository) as T
}
