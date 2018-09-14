package com.ascending.games.id1.view

import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.id1.model.board.WallState
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.view.AView2
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class RoomView(val room : Room, val shapeRenderer: ShapeRenderer) : AView2(0) {
    override fun render(batch: SpriteBatch, camera: Camera) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(Color.BROWN)
        for (roomElement in room.roomElements) {
            val roomElementPosition = getRoomElementPosition(roomElement)
            shapeRenderer.rect(roomElementPosition.x, roomElementPosition.y, BoardView.TILE_SIZE, BoardView.TILE_SIZE)
        }
        shapeRenderer.end()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        for (roomElement in room.roomElements) {
            val roomElementPosition = getRoomElementPosition(roomElement)
            for (wall in roomElement.walls) {
                when (wall.wallState) {
                    WallState.CLOSED -> shapeRenderer.setColor(Color.GRAY)
                    WallState.DOOR -> shapeRenderer.setColor(Color.RED)
                }

                when (wall.direction) {
                    Direction4.LEFT -> shapeRenderer.line(roomElementPosition.x, roomElementPosition.y, roomElementPosition.x, roomElementPosition.y + BoardView.TILE_SIZE)
                    Direction4.RIGHT -> shapeRenderer.line(roomElementPosition.x + BoardView.TILE_SIZE, roomElementPosition.y, roomElementPosition.x + BoardView.TILE_SIZE, roomElementPosition.y + BoardView.TILE_SIZE)
                    Direction4.DOWN -> shapeRenderer.line(roomElementPosition.x, roomElementPosition.y, roomElementPosition.x + BoardView.TILE_SIZE, roomElementPosition.y)
                    Direction4.UP -> shapeRenderer.line(roomElementPosition.x, roomElementPosition.y + BoardView.TILE_SIZE, roomElementPosition.x + BoardView.TILE_SIZE, roomElementPosition.y + BoardView.TILE_SIZE)
                }
            }
        }
        shapeRenderer.end()
    }

    fun getRoomElementPosition(roomElement : RoomElement) : Vector2 {
        return Vector2((roomElement.boardCoord.x + BoardView.OFFSET.x) * BoardView.TILE_SIZE, (roomElement.boardCoord.y + BoardView.OFFSET.y) * BoardView.TILE_SIZE)
    }

    override fun dispose() = Unit
}