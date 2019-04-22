package com.iammert.library.ui.blendlib

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.iammert.library.ui.blendlib.Direction.*
import com.iammert.library.ui.blendlib.Shape.*

class Blend private constructor(builder: Builder) {

    private val colorList: IntArray
    private val direction: Direction
    private val shape: Shape
    private val gradientDrawable: GradientDrawable

    init {
        this.colorList = builder.colorList
        this.direction = builder.direction
        this.shape = builder.shape
        gradientDrawable = createGradientDrawable()
    }

    fun getGradient(): GradientDrawable = gradientDrawable

    private fun createGradientDrawable() =
        GradientDrawable().apply {
            orientation = createOrientation()
            shape = createGradientShape()
            colors = colorList
        }

    private fun createOrientation() = when (direction) {
        TOP_TO_BOTTOM -> GradientDrawable.Orientation.TOP_BOTTOM
        BOTTOM_TO_TOP -> GradientDrawable.Orientation.BOTTOM_TOP
        LEFT_TO_RIGHT -> GradientDrawable.Orientation.LEFT_RIGHT
        RIGHT_TO_LEFT -> GradientDrawable.Orientation.RIGHT_LEFT
        TOP_RIGHT_TO_BOTTOM_LEFT -> GradientDrawable.Orientation.TR_BL
        TOP_LEFT_TO_BOTTOM_RIGHT -> GradientDrawable.Orientation.TL_BR
        BOTTOM_LEFT_TO_TOP_RIGHT -> GradientDrawable.Orientation.BL_TR
        BOTTOM_RIGHT_TO_TOP_LEFT -> GradientDrawable.Orientation.BR_TL
    }

    private fun createGradientShape() = when (shape) {
        OVAL -> GradientDrawable.OVAL
        RECTANGLE -> GradientDrawable.RECTANGLE
    }

    class Builder {
        var colorList: IntArray = intArrayOf()
            private set

        var direction = TOP_LEFT_TO_BOTTOM_RIGHT
            private set

        var shape = RECTANGLE
            private set

        fun addIntColors(vararg colors: Int) = apply {
            val array = arrayListOf<Int>()
            colorList.toCollection(array)
            colors.forEach { array.add(it) }
            colorList = array.toIntArray()
        }

        fun addHexColors(vararg colors: String) = apply {
            val array = arrayListOf<Int>()
            colorList.toCollection(array)
            colors.forEach { array.add(Color.parseColor(it)) }
            colorList = array.toIntArray()
        }

        fun shape(shape: Shape) = apply { this.shape = shape }

        fun direction(direction: Direction) = apply { this.direction = direction }

        fun build() = Blend(this)
    }

}