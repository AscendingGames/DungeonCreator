package com.ascending.games.engine.model.geometry

import com.badlogic.gdx.math.Rectangle

interface IRectangle2 : IPosition2, ISize2 {
    val rectangle : Rectangle
            get() = Rectangle(position.x, position.y, size.x, size.y)
}