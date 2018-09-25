package com.ascending.games.lib.model.geometry

import com.badlogic.gdx.math.Vector2

data class Rectangle2(override val position : Vector2, override var size : Vector2) : IRectangle2 {

}