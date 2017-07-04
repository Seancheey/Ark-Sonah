package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import javafx.animation.AnimationTimer
import javafx.scene.control.ProgressBar

/**
 * Created by Seancheey on 04/07/2017.
 * GitHub: https://github.com/Seancheey
 */
class ProgressBarNode(val timeInSecond: Double, field: Battlefield, val onProgressEnd: () -> Unit = {}) : GuiNode(ProgressBar(), field) {
    private val progressGui: ProgressBar
        get() = gui as ProgressBar

    private var startTime = -1L
    private val progressTimer: AnimationTimer = object : AnimationTimer() {
        override fun start() {
            super.start()
            startTime = System.currentTimeMillis()
        }

        override fun handle(now: Long) {
            val progress = timeInSecond / (now - startTime)
            if (progress < 1) {
                progressGui.progress = progress
            } else {
                progressGui.progress = 1.0
                onProgressEnd()
                stop()
            }
        }
    }

    init {
        progressTimer.start()
    }
}