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
        batch.begin()
        if (room.isCleared) batch.setColor(Color.GRAY) else batch.setColor(Color.WHITE)
        for (roomElement in room.roomElements) {
            val spriteView = SpriteView(BoardRectangle(roomElement), toolkit.textureManager.getTexture("roombg.png"), 0)
            spriteView.render(batch, camera)
        }

        for (aClearable in room.allRoomClearables) {
            val textureName = when (aClearable) {
                is Monster -> "monster1.png"
                is Crystal -> "heal.png"
                is StairsDown -> "stairsdown.png"
                else -> "UNDEFINED"
            }

            val spriteView = SpriteView(BoardRectangle(aClearable), toolkit.textureManager.getTexture(textureName), 0)
            spriteView.render(batch, camera)
        }
        batch.end()

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