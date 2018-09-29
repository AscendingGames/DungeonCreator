package com.ascending.games.id1.view.board

import com.ascending.games.id1.model.board.*
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.view.AView2
import com.ascending.games.lib.view.SpriteView
import com.ascending.games.lib.view.Toolkit
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class RoomView(val room : Room, private val shapeRenderer: ShapeRenderer, private val toolkit : Toolkit) : AView2(0) {
    override fun render(batch: SpriteBatch, camera: Camera) {
        val isCleared = room.isCleared

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        for (roomElement in room.roomElements) {
            if (isCleared) shapeRenderer.setColor(Color.BROWN) else shapeRenderer.setColor(Color.GOLD)
            val roomElementPosition = BoardView.convertToScreenCoordinates(roomElement.boardCoord)
            shapeRenderer.rect(roomElementPosition.x, roomElementPosition.y, BoardView.TILE_SIZE, BoardView.TILE_SIZE)

            for (aRoomContent in roomElement.clearables) {
                if (aRoomContent is Monster) {
                    shapeRenderer.end()
                    batch.begin()
                    val spriteView = SpriteView(BoardRectangle(aRoomContent), toolkit.textureManager.getTexture("monster1.png"), 0)
                    spriteView.render(batch, camera)
                    batch.end()
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
                }  else if (aRoomContent is Crystal) {
                    shapeRenderer.setColor(Color.VIOLET)
                    shapeRenderer.rect(roomElementPosition.x, roomElementPosition.y, BoardView.TILE_SIZE, BoardView.TILE_SIZE)
                } else if (aRoomContent is StairsDown) {
                    shapeRenderer.setColor(Color.WHITE)
                    shapeRenderer.rect(roomElementPosition.x, roomElementPosition.y, BoardView.TILE_SIZE, BoardView.TILE_SIZE)
                }
            }
        }
        shapeRenderer.end()

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        Gdx.gl.glLineWidth(BoardView.BOARD_LINE_SIZE)
        for (roomElement in room.roomElements) {
            val roomElementPosition = BoardView.convertToScreenCoordinates(roomElement.boardCoord)
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
}