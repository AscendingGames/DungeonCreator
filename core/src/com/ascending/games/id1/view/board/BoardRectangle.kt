package com.ascending.games.id1.view.board

import com.ascending.games.lib.model.geometry.IRectangle2
import com.badlogic.gdx.math.Vector2

class BoardRectangle(val boardRectangle : IRectangle2) : IRectangle2 {
    override val position: Vector2
        get() = BoardView.convertToScreenCoordinates(boardRectangle.position)
    override var size: Vector2
        get() = Vector2(boardRectangle.size.x * BoardView.TILE_SIZE, boardRectangle.size.y * BoardView.TILE_SIZE)
        set(_) {}
}