package com.captechventures.transportdirectory.data

import com.captechventures.transportdirectory.utilities.runOnIoThread

/**
 * Repository for handling data operations.
 */
class CarRepository private constructor(private val carDao: CarDao) {

    fun getCars() = carDao.getCars()

    fun getCarByVin(vin: String) = carDao.getCarByVin(vin)

    fun getFavoriteCars() = carDao.getCarsByFavorite()

    fun updateCarFavoriteFlag(vin: String, favorite: Boolean) {
        runOnIoThread {
            carDao.updateCarFavoriteFlag(vin, if (favorite) 0 else 1)
        }
    }

    //Extra unused examples of using the DAO.
    fun getCarsByModelYear(year: Int) = carDao.getCarsByModelYear(year)

    fun getCarsByManufacturer(manufacturer: String) = carDao.getCarsByManufacturer(manufacturer)

    fun getCarsByModel(model: String) = carDao.getCarsByModel(model)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CarRepository? = null

        fun getInstance(carDao: CarDao) =
                instance ?: synchronized(this) {
                    instance ?: CarRepository(carDao).also { instance = it }
                }
    }
}
