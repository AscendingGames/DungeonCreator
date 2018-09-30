package com.ascending.games.id1.view.board

import com.ascending.games.id1.model.board.*
import com.ascending.games.engine.view.SpriteView
import com.ascending.games.engine.view.Toolkit
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class RoomView(val room : Room, private val toolkit : Toolkit) {

    private fun createRoomElementView(roomElement : RoomElement) : SpriteView {
        return SpriteView(BoardRectangle(roomElement), toolkit.textureManager.getTexture("roombg.png"))
    }

    private fun createRoomClearableView(aClearable: AClearable) : SpriteView {
        val textureName = when (aClearable) {
            is Monster -> "monster1.png"
            is Crystal -> "heal.png"
            is StairsDown -> "stairsdown.png"
            else -> "UNDEFINED"
        }

        return SpriteView(BoardRectangle(aClearable), toolkit.textureManager.getTexture(textureName))
    }

    private fun createWallView(wall : Wall) : SpriteView {
        return SpriteView(BoardRectangle(wall), toolkit.textureManager.getTexture("wall.png"))
    }

    fun renderRoomElements(batch: SpriteBatch, camera: Camera) {
        val roomElementViews = room.roomElements.map { createRoomElementView(it) }
        val baseColor = if (room.isCleared) Color.GRAY.cpy() else Color.WHITE.cpy()
        val typeColor = when (room.type) {
            RoomType.NORMAL -> Color.WHITE
            RoomType.DANGER_HIGH -> Color.FIREBRICK
            RoomType.DANGER -> Color.PINK
            RoomType.TREASURY_HIGH ->  Color.GOLD
            RoomType.TREASURY -> Color.GOLDENROD
            RoomType.BOSS ->  Color.VIOLET
        }
        batch.color = baseColor.mul(typeColor)

        roomElementViews.forEach { it.render(batch, camera) }
        batch.color = Color.WHITE
    }

    fun renderClearables(batch: SpriteBatch, camera: Camera) {
        val roomClearableViews = room.allRoomClearables.map { createRoomClearableView(it) }
        roomClearableViews.forEach { it.render(batch, camera) }
    }

    fun renderWalls(batch: SpriteBatch, camera: Camera) {
        val roomWallViews = room.allWalls.map { createWallView(it) }
        for (wallView in roomWallViews) {
            val wall = (wallView.rectangleProvider as BoardRectangle).boardRectangle as Wall
            when (wall.wallState) {
                WallState.CLOSED -> batch.color = Color.GRAY
                WallState.DOOR -> batch.color = Color.RED
            }
            wallView.render(batch, camera)
        }
    }
}