package com.ranis.game_library

import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton

class MemoryFlipAnimation {
    fun flipCard(button: ImageButton, imageResId: Int) {
        button.animate()
            .withLayer()
            .rotationY(90f)
            .setInterpolator(DecelerateInterpolator())
            .setDuration(300)
            .withEndAction {
                button.setBackgroundResource(imageResId)
                button.rotationY = -90f
                button.animate()
                    .withLayer()
                    .rotationY(0f)
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator())
                    .start()
            }
            .start()
    }

    public fun flipCardBack(button: ImageButton) {
        button.animate()
            .withLayer()
            .rotationY(90f)
            .setInterpolator(DecelerateInterpolator())
            .setDuration(300)
            .withEndAction {
                button.setBackgroundResource(R.drawable.ic_launcher_background)
                button.rotationY = -90f
                button.animate()
                    .withLayer()
                    .rotationY(0f)
                    .setDuration(300)
                    .setInterpolator(DecelerateInterpolator())
                    .start()
            }
            .start()
    }
}