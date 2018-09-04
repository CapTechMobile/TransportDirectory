package com.captechventures.transportdirectory.adapters

import android.support.v7.util.DiffUtil
import com.captechventures.transportdirectory.data.Car

class CarDiffCallback : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.vin == newItem.vin
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}