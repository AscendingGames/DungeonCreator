package com.ascending.games.id1.view

import com.ascending.games.id1.model.board.Board
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.view.AView2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class BoardView(val board : Board) : AView2(0) {

    companion object  {
        val OFFSET : Coord2 = Coord2(2, 2)
        val TILE_SIZE : Float = 50f
        val BOARD_COLOR = Color.GRAY
        val BOARD_LINE_SIZE = 10f
    }

    val shapeRenderer = ShapeRenderer()
    val boardArea = Rectangle(OFFSET.x * TILE_SIZE, OFFSET.y * TILE_SIZE, board.width * TILE_SIZE, board.height * TILE_SIZE)

    override fun render(batch: SpriteBatch, camera : Camera) {
        shapeRenderer.projectionMatrix = camera.combined

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(1.0f, 0f, 0f, 1.0f)
        for (room in board.rooms) {
            for (roomElement in room.roomElements) {
                val boardCoord = board.getBoardCoord(roomElement)
                val position = Vector2((boardCoord.x + OFFSET.x) * TILE_SIZE, (boardCoord.y + OFFSET.y) * TILE_SIZE)
                shapeRenderer.rect(position.x, position.y, TILE_SIZE, TILE_SIZE)
            }
        }
        shapeRenderer.end()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        Gdx.gl.glLineWidth(BOARD_LINE_SIZE)
        shapeRenderer.setColor(BOARD_COLOR.r, BOARD_COLOR.g, BOARD_COLOR.b, BOARD_COLOR.a)
        shapeRenderer.rect(boardArea.x, boardArea.y, boardArea.width, boardArea.height)
        shapeRenderer.end()
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}