package com.captechventures.transportdirectory.adapters

import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.ImageView
import com.captechventures.transportdirectory.R


@BindingAdapter("logoName")
fun logoName(view: ImageView, logoName: String?) {
    val res = when (logoName) {
        "Honda" -> R.drawable.honda
        "Toyota" -> R.drawable.toyota
        "Volkswagen" -> R.drawable.volkswagen
        else -> R.drawable.ic_car
    }

    view.setImageResource(res)
}

@BindingAdapter("imageName")
fun imageName(view: ImageView, imageName: String?) {

    if (imageName.isNullOrEmpty()) {
        view.setImageResource(R.drawable.civic)
    } else {
        val context = view.context
        val res = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        view.setImageResource(res)
    }

}

@BindingAdapter("carColor")
fun carColor(view: View, color: String?) {

    if (color.isNullOrEmpty()) {
        view.setBackgroundColor(Color.WHITE)
    } else {
        val colorId = when (color) {
            "Black" -> Color.BLACK
            "White" -> Color.WHITE
            "Gray" -> Color.DKGRAY
            "Silver" -> Color.GRAY
            else -> Color.WHITE
        }

        val border = GradientDrawable()
        border.setColor(colorId)
        border.setStroke(1, -0x1000000) //black border with full opacity
        view.setBackground(border)

    }

}

@BindingAdapter("highlightIf")
fun hightlightIf(fab: FloatingActionButton, isFavorite: Boolean?) {

    if (isFavorite != null && isFavorite) {
        fab.setColorFilter(Color.YELLOW)
    } else {
        fab.setColorFilter(Color.LTGRAY)
    }
}

