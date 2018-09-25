package com.ascending.games.id1.view.board

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.RoomPool
import com.ascending.games.lib.view.AView2
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class RoomPoolView (val roomPool : RoomPool, val shapeRenderer: ShapeRenderer) : AView2(0) {

    private val roomViews = MutableList(BoardDomain.COUNT_WAITING_ROOMS) { RoomView(roomPool.waitingRooms[it], shapeRenderer) }

    init {
        roomPool.waitingRooms.onAdd += { _, room ->
            roomViews.removeAt(0)
            roomViews.add(RoomView(room, shapeRenderer))
        }
    }

    override fun render(batch: SpriteBatch, camera: Camera) {
        roomViews.forEach { it.render(batch, camera) }
    }
}