package com.captechventures.transportdirectory.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.captechventures.transportdirectory.R
import com.captechventures.transportdirectory.databinding.FragmentCarDetailBinding
import com.captechventures.transportdirectory.utilities.InjectorUtils
import com.captechventures.transportdirectory.viewmodels.CarDetailViewModel

/**
 * A fragment representing a car detail screen.
 */
class CarDetailFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val vin = CarDetailFragmentArgs.fromBundle(arguments).vin

        val factory = InjectorUtils.provideCarDetailViewModelFactory(requireActivity(), vin)
        val carDetailViewModel = ViewModelProviders.of(this, factory)
                .get(CarDetailViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentCarDetailBinding>(
                inflater, R.layout.fragment_car_detail, container, false).apply {
            viewModel = carDetailViewModel
            setLifecycleOwner(this@CarDetailFragment)

            fabFavorite.setOnClickListener { view ->
                carDetailViewModel.updateCarFavoriteFlag()

                val stringRes = if(carDetailViewModel.isCarFavorited()) R.string.remove_from_favorite else R.string.add_to_favorite

                Snackbar.make(view, stringRes, Snackbar.LENGTH_LONG).show()

            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

}
