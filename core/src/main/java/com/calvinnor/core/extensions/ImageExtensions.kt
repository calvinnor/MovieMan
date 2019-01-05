package com.calvinnor.core.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/** Loads an image into the ImageView **/
fun ImageView.setImage(
    imageUrl: String,
    scaleType: ScaleType = ScaleType.FIT_CENTER,
    onSuccess: (drawable: Drawable) -> Unit = {},
    onFailure: () -> Unit = {}

) {
    Glide.with(context)
        .load(imageUrl)
        .apply(
            when (scaleType) {
                ScaleType.FIT_CENTER -> RequestOptions.fitCenterTransform()
                ScaleType.CENTER_CROP -> RequestOptions.centerCropTransform()
            }
        )
        .addListener(object : RequestListener<Drawable> {

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ) = true

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let { onSuccess(it) }
                return false
            }

        })
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
