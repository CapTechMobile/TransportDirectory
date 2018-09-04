package com.captechventures.transportdirectory.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.*
import com.captechventures.transportdirectory.R
import com.captechventures.transportdirectory.adapters.CarAdapter
import com.captechventures.transportdirectory.databinding.FragmentCarListBinding
import com.captechventures.transportdirectory.utilities.InjectorUtils
import com.captechventures.transportdirectory.viewmodels.CarListViewModel

class CarListFragment : Fragment() {

    private lateinit var viewModel: CarListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val factory = InjectorUtils.provideCarListViewModelFactory(requireActivity())
        val carListViewModel = ViewModelProviders.of(this, factory).get(CarListViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentCarListBinding>(
                inflater, R.layout.fragment_car_list, container, false).apply {
            viewModel = carListViewModel
            setLifecycleOwner(this@CarListFragment)
        }

        val adapter = CarAdapter()
        binding.root.findViewById<RecyclerView>(R.id.car_list).adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_car_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorites -> {
                with(viewModel) {
                    toggleFavorite()
                    item.icon.setTint(if(shouldShowFavorites()) Color.YELLOW else Color.GRAY)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: CarAdapter) {
        viewModel.getCars().observe(viewLifecycleOwner, Observer { cars ->
            if (cars != null) adapter.submitList(cars)
        })
    }
}