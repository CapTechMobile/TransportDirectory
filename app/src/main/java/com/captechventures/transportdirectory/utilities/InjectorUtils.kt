package com.captechventures.transportdirectory.utilities

import android.content.Context
import com.captechventures.transportdirectory.data.CarRepository
import com.captechventures.transportdirectory.data.TransportDatabase
import com.captechventures.transportdirectory.viewmodels.CarDetailViewModelFactory
import com.captechventures.transportdirectory.viewmodels.CarListViewModelFactory

/**
 * Static methods used to inject classes needed for Fragments.
 */
object InjectorUtils {

    private fun getCarRepository(context: Context): CarRepository {
        return CarRepository.getInstance(TransportDatabase.getInstance(context).carDao())
    }

    fun provideCarListViewModelFactory(context: Context): CarListViewModelFactory {
        val repository = getCarRepository(context)
        return CarListViewModelFactory(repository)
    }

    fun provideCarDetailViewModelFactory(
            context: Context,
            vin: String
    ): CarDetailViewModelFactory {
        return CarDetailViewModelFactory(getCarRepository(context), vin)
    }
}
