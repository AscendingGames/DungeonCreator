package com.ascending.games.lib.model.geometry

import com.ascending.games.lib.model.game.IChild
import com.badlogic.gdx.math.Vector2

interface IHierarchical2 : IPosition2, IChild {
    var relativePosition : Vector2
    override val position: Vector2
        get() =  (parent?.let { if (it is IPosition2) it.position else Vector2() } ?: Vector2()).add(relativePosition)
}