package com.ascending.games.id1.view.board

import com.ascending.games.id1.model.board.*
import com.ascending.games.lib.view.AView2
import com.ascending.games.lib.view.SpriteView
import com.ascending.games.lib.view.Toolkit
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class RoomView(val room : Room, private val toolkit : Toolkit) : AView2(0) {

    private val roomElementViews = mutableListOf<SpriteView>()
    private val roomClearableViews = mutableListOf<SpriteView>()
    private val roomWallViews = mutableListOf<SpriteView>()

    init {
        initRoomElements()
        initRoomClearables()
        initRoomWalls()
    }

    private fun initRoomElements() {
        room.roomElements.onAdd += { _, roomElement ->
            roomElementViews.add(createRoomElementView(roomElement))
        }
        room.roomElements.onRemove += { roomElement ->
            roomElementViews.removeAll(roomElementViews.filter { (it.rectangleProvider as BoardRectangle).boardRectangle == roomElement })
        }
        roomElementViews.addAll(room.roomElements.map { createRoomElementView(it) })
    }

    private fun initRoomClearables() {
        for (roomElement in room.roomElements) {
            roomElement.clearables.onAdd += { _, clearable ->
                roomClearableViews.add(createRoomClearableView(clearable))
            }
            roomElement.clearables.onRemove += { clearable ->
                roomClearableViews.removeAll(roomClearableViews.filter { (it.rectangleProvider as BoardRectangle).boardRectangle == clearable })
            }
        }
        roomClearableViews.addAll(room.allRoomClearables.map { createRoomClearableView(it) })
    }

    private fun initRoomWalls() {
        for (roomElement in room.roomElements) {
            roomElement.walls.onRemove += { wall ->
                roomWallViews.removeAll(roomWallViews.filter { (it.rectangleProvider as BoardRectangle).boardRectangle == wall })
            }
        }
        roomWallViews.addAll(room.allWalls.map { createWallView(it) })
    }

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

    override fun render(batch: SpriteBatch, camera: Camera) {
        if (room.isCleared) batch.color = Color.GRAY else batch.color = Color.WHITE
        roomElementViews.forEach { it.render(batch, camera) }
        roomClearableViews.forEach { it.render(batch, camera) }

        for (wallView in roomWallViews) {
            val wall = (wallView.rectangleProvider as BoardRectangle).boardRectangle as Wall
            when (wall.wallState) {
                WallState.CLOSED -> batch.color = Color.GRAY
                WallState.DOOR -> batch.color = Color.RED
            }
            wallView.render(batch, camera)
        }

        batch.color = Color.WHITE
    }
}