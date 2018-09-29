package com.ascending.games.id1.view.board

import com.ascending.games.lib.model.game.AGameObject
import com.ascending.games.lib.model.geometry.IRectangle2
import com.badlogic.gdx.math.Vector2

class BoardRectangle(private val gameObject : AGameObject) : IRectangle2 {
    override val position: Vector2
        get() = BoardView.convertToScreenCoordinates(gameObject.position)
    override var size: Vector2
        get() = Vector2(gameObject.size.x * BoardView.TILE_SIZE, gameObject.size.y * BoardView.TILE_SIZE)
        set(_) {}
}