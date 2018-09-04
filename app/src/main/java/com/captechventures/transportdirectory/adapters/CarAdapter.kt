package com.captechventures.transportdirectory.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.captechventures.transportdirectory.data.Car
import com.captechventures.transportdirectory.databinding.ListItemCarBinding
import com.captechventures.transportdirectory.fragments.CarListFragmentDirections

/**
 * Adapter for the [RecyclerView] in [CarListFragment].
 */
class CarAdapter : ListAdapter<Car, CarAdapter.ViewHolder>(CarDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = getItem(position)
        holder.apply {
            bind(createOnClickListener(car.vin), car)
            itemView.tag = car
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCarBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(vin: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = CarListFragmentDirections.ActionCarListFragmentToCarDetailFragment(vin)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
            private val binding: ListItemCarBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Car) {
            binding.apply {
                clickListener = listener
                car = item
                executePendingBindings()
            }
        }
    }
}