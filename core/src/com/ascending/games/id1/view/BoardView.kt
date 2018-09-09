package com.ascending.games.id1.view

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.WallState
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
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
        const val TILE_SIZE : Float = 50f
        val BOARD_COLOR : Color = Color.GRAY
        const val BOARD_LINE_SIZE = 10f
        const val WALL_LINE_SIZE = 3f
    }

    private val shapeRenderer = ShapeRenderer()
    private val boardArea = Rectangle(OFFSET.x * TILE_SIZE, OFFSET.y * TILE_SIZE, board.width * TILE_SIZE, board.height * TILE_SIZE)

    override fun render(batch: SpriteBatch, camera : Camera) {
        shapeRenderer.projectionMatrix = camera.combined

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(Color.BROWN)
        for (room in board.rooms) {
            for (roomElement in room.roomElements) {
                val boardCoord = roomElement.getBoardCoord()
                val roomElementPosition = Vector2((boardCoord.x + OFFSET.x) * TILE_SIZE, (boardCoord.y + OFFSET.y) * TILE_SIZE)
                shapeRenderer.rect(roomElementPosition.x, roomElementPosition.y, TILE_SIZE,  TILE_SIZE)
            }
        }
        shapeRenderer.end()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        Gdx.gl.glLineWidth(WALL_LINE_SIZE)
        for (room in board.rooms) {
            for (roomElement in room.roomElements) {
                val boardCoord = roomElement.getBoardCoord()
                val roomElementPosition = Vector2((boardCoord.x + OFFSET.x) * TILE_SIZE, (boardCoord.y + OFFSET.y) * TILE_SIZE)
                for (wall in roomElement.walls) {
                    when (wall.wallState) {
                        WallState.CLOSED -> shapeRenderer.setColor(Color.GRAY)
                        WallState.DOOR -> shapeRenderer.setColor(Color.RED)
                    }

                    when (wall.direction) {
                        Direction4.LEFT -> shapeRenderer.line(roomElementPosition.x, roomElementPosition.y, roomElementPosition.x, roomElementPosition.y + TILE_SIZE)
                        Direction4.RIGHT -> shapeRenderer.line(roomElementPosition.x + TILE_SIZE, roomElementPosition.y, roomElementPosition.x + TILE_SIZE, roomElementPosition.y + TILE_SIZE)
                        Direction4.DOWN -> shapeRenderer.line(roomElementPosition.x, roomElementPosition.y, roomElementPosition.x + TILE_SIZE, roomElementPosition.y)
                        Direction4.UP -> shapeRenderer.line(roomElementPosition.x, roomElementPosition.y + TILE_SIZE, roomElementPosition.x + TILE_SIZE, roomElementPosition.y + TILE_SIZE)
                    }
                }
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