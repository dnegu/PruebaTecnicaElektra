package com.dnegu.pruebatecnica.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.provider.CalendarContract
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dnegu.pruebatecnica.R

class CircularImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {
    private var initials: String? = null
    private var placeholder: Drawable? = null
    private var backgroundColor: Int = Color.GRAY
    private var textColor: Int = Color.WHITE
    private var textSize: Float = 50f
    private var minWidth = 200
    private var minHeight = 200

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView)
        backgroundColor = a.getColor(R.styleable.CircularImageView_initialsBackgroundColor, Color.GRAY)
        textColor = a.getColor(R.styleable.CircularImageView_initialsTextColor, Color.WHITE)
        placeholder = a.getDrawable(R.styleable.CircularImageView_placeholderDrawable)
        textSize = a.getDimension(R.styleable.CircularImageView_initialsTextSize, 50f)
        a.recycle()
    }

    fun loadImage(url: String?, name: String?, textColor: Int = Color.WHITE, backgroundColor: Int = Color.CYAN) {
        this.textColor = textColor
        this.backgroundColor = backgroundColor

        initials = getInitials(name)

        if (url.isNullOrEmpty()) {
            if(initials?.isNotEmpty() == true)
                showInitials()
            else showPlaceholder()
        } else {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(RequestOptions().circleCrop())
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        setImageBitmap(resource)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        showInitials()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }

    private fun showPlaceholder() {
        if (placeholder != null) {
            setImageDrawable(placeholder)
        } else {
            // Draw a default placeholder if none is provided
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            // Draw background
            val paint = Paint()
            paint.color = Color.LTGRAY
            paint.style = Paint.Style.FILL
            canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint)

            // Draw a default placeholder text or image
            paint.color = Color.DKGRAY
            paint.textSize = textSize
            paint.textAlign = Paint.Align.CENTER
            val bounds = Rect()
            val placeholderText = "?"
            paint.getTextBounds(placeholderText, 0, placeholderText.length, bounds)
            val x = width / 2f
            val y = height / 2f - bounds.exactCenterY()
            canvas.drawText(placeholderText, x, y, paint)

            setImageBitmap(bitmap)
        }
    }

    private fun showInitials() {
        val width = if (width < minWidth) minWidth else width
        val height = if (height < minHeight) minHeight else height

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Draw background
        val paint = Paint()
        paint.color = backgroundColor
        paint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint)

        // Draw initials
        paint.color = textColor
        paint.textSize = textSize
        paint.textAlign = Paint.Align.CENTER
        val bounds = Rect()
        paint.getTextBounds(initials, 0, initials?.length ?: 0, bounds)
        val x = width / 2f
        val y = height / 2f - bounds.exactCenterY()
        canvas.drawText(initials ?: "", x, y, paint)

        setImageBitmap(bitmap)
    }

    private fun getInitials(name: String?): String? {
        if (name.isNullOrEmpty()) return null
        val words = name.split(" ")
        return when {
            words.size == 1 -> words[0].substring(0, 1).uppercase()
            words.size > 1 -> words[0].substring(0, 1).uppercase() + words[1].substring(0, 1).uppercase()
            else -> null
        }
    }
}