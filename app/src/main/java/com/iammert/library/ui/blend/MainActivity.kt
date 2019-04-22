package com.iammert.library.ui.blend

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.iammert.library.ui.blendlib.Blend
import com.iammert.library.ui.blendlib.Direction
import com.iammert.library.ui.blendlib.Shape

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        val blend = Blend.Builder()
            .shape(Shape.RECTANGLE)
            .direction(Direction.TOP_LEFT_TO_BOTTOM_RIGHT)
            .addHexColors("#235678", "#000000", "#ffffff", "#000000", "#623653")
            .build()

        imageView.background = blend.getGradient()
    }
}
