package com.ascending.games.id1.edit.board.action

import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2

class GestureActionProvider : GestureDetector.GestureListener {

    companion object {
        const val PAN_THRESHOLD_X = 2f
        const val PAN_THRESHOLD_Y = 20f
    }

    val actionBuffer = mutableListOf<IBoardAction>()
    private var canDrop = true

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return false
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        val absDeltaX = Math.abs(deltaX)
        val absDeltaY = Math.abs(deltaY)

        if (absDeltaX > absDeltaY && absDeltaX > PAN_THRESHOLD_X) {
            actionBuffer.add(SlideAction(Math.signum(deltaX).toInt()))
            return true
        } else if (absDeltaY > absDeltaX && absDeltaY > PAN_THRESHOLD_Y && deltaY > 0 && canDrop) {
            actionBuffer.add(DropAction())
            canDrop = false
            return true
        }

        return false
    }

    override fun pinchStop() {

    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        actionBuffer.add(RotateAction())
        return true
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        canDrop = true
        return true
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return false
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return false
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return false
    }
}