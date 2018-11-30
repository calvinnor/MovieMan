package com.calvinnor.movieman.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/** Loads an image into the ImageView **/
fun ImageView.setImage(imageUrl: String, scaleType: ScaleType = ScaleType.FIT_CENTER) {
    Glide.with(context)
        .load(imageUrl)
        .apply(
            when (scaleType) {
                ScaleType.FIT_CENTER -> RequestOptions.fitCenterTransform()
                ScaleType.CENTER_CROP -> RequestOptions.centerCropTransform()
            }
        )
        .into(this)
}

enum class ScaleType {

    /**
     * Crop the image about the center. Either cropped horizontally or vertically depending on AR of ImageView & image.
     * This will not leave any empty around the ImageView.
     */
    CENTER_CROP,

    /**
     * Fit the image in the designated bounds.
     * Produces empty space depending on AR of ImageView & image.
     */
    FIT_CENTER

}
