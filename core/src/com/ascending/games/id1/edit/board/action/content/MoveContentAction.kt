package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.engine.edit.action.ITimedAction
import com.badlogic.gdx.math.Vector2

class MoveContentAction(val aRoomContent: ARoomContent, val targetRoomElement : RoomElement) : com.ascending.games.engine.edit.action.ITimedAction {
    override val canExecute : Boolean
        get() = targetRoomElement.room.roomElements.contains(targetRoomElement)

    override fun execute(delta: Float) : Boolean {
        val targetPosition = targetRoomElement.position
        val movementVector = targetPosition.sub(aRoomContent.position)
        val distance = movementVector.len()
        val speed = aRoomContent.stats[StatType.SPEED.name] ?: 1f
        val movedDistance = delta * speed

        if (distance <= movedDistance) {
            aRoomContent.roomElement = targetRoomElement
            aRoomContent.relativePosition = Vector2()
            targetRoomElement.room.isVisited = true
            return true
        } else {
            val direction = movementVector.nor()
            aRoomContent.relativePosition = Vector2(
                    aRoomContent.relativePosition.x + direction.x * movedDistance,
                    aRoomContent.relativePosition.y + direction.y * movedDistance)
            return false
        }
    }
}