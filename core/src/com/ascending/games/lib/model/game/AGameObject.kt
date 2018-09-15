package com.ascending.games.lib.model.game

import com.badlogic.gdx.math.Vector2

abstract class AGameObject : IPosition2, IChild {
    override var relativePosition = Vector2()
    override val absolutePosition: Vector2
        get() =  (parent?.let { if (it is IPosition2) it.absolutePosition else Vector2() } ?: Vector2()).add(relativePosition)
}