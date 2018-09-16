package com.ascending.games.id1.view.board

import com.ascending.games.id1.model.board.Room
import com.ascending.games.lib.view.AView2
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class ProjectedRoomView(val room : Room, val shapeRenderer: ShapeRenderer) : AView2(0) {

    companion object {
        const val TILE_SIZE_SCALE = 0.8f
        const val PROJECTED_TILE_SIZE = BoardView.TILE_SIZE * TILE_SIZE_SCALE
        const val PROJECTED_TILE_OFFSET = (BoardView.TILE_SIZE - PROJECTED_TILE_SIZE) / 2
    }

    override fun render(batch: SpriteBatch, camera: Camera) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.setColor(Color.LIGHT_GRAY)
        for (roomElement in room.roomElements) {
            val roomElementPosition = BoardView.convertToScreenCoordinates(roomElement.boardCoord)
            shapeRenderer.rect(roomElementPosition.x + PROJECTED_TILE_OFFSET, roomElementPosition.y + PROJECTED_TILE_OFFSET, PROJECTED_TILE_SIZE, PROJECTED_TILE_SIZE)
        }
        shapeRenderer.end()
    }

    override fun dispose() {

    }
}