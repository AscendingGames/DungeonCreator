package com.ascending.games.engine.model.geometry

import com.ascending.games.engine.model.game.IChild
import com.badlogic.gdx.math.Vector2

interface IHierarchical2 : IPosition2, IChild {
    val relativePosition : Vector2
    override val position: Vector2
        get() =  (parent?.let { if (it is IPosition2) it.position.cpy() else Vector2() } ?: Vector2()).add(relativePosition)
}