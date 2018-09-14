package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.IPosition2
import com.badlogic.gdx.math.Vector2

abstract class ARoomContent : IPosition2{
    lateinit var roomElement : RoomElement

    override var relativePosition = Vector2()
    override val absolutePosition: Vector2
        get() = Vector2(relativePosition.x + roomElement.boardX.toFloat(), relativePosition.y + roomElement.boardY.toFloat())
}