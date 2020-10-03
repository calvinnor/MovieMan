package com.calvinnor.core.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.calvinnor.core.R

fun ImageView.defaultImage() {

    setBackgroundColor(
        ContextCompat.getColor(
            context,
            R.color.colorAccent
        )
    )

    setImageResource(R.drawable.ic_image)
}

/** Loads an image into the ImageView **/
fun ImageView.setImage(
    imageUrl: String,
    scaleType: ScaleType = ScaleType.FIT_CENTER,
    fadeIn: Boolean = true,
    onSuccess: (drawable: Drawable) -> Unit = {},
    onFailure: () -> Unit = {}

) {

    if (imageUrl.isEmpty()) {
        onFailure()
        return
    }

    Glide.with(context)
        .load(imageUrl)
        .apply(
            when (scaleType) {
                ScaleType.FIT_CENTER -> RequestOptions.fitCenterTransform()
                ScaleType.CENTER_CROP -> RequestOptions.centerCropTransform()
            }
        )
        .apply {
            if (fadeIn) transition(DrawableTransitionOptions.withCrossFade())
        }
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

fun Context.getBitmapDrawable(imageUrl: String, onSuccess: (drawable: Bitmap) -> Unit = {}) {
    Glide.with(this)
        .asBitmap()
        .load(imageUrl)
        .into(object : CustomTarget<Bitmap>() {

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                onSuccess(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // NO-OP
            }
        })
}

fun ImageView.setTint(@ColorRes colorRes: Int) =
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(context.colorFrom(colorRes)));

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
