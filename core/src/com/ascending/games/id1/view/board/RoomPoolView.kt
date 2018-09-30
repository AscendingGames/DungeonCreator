package com.ascending.games.id1.view.board

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.RoomPool
import com.ascending.games.lib.view.AView2
import com.ascending.games.lib.view.Toolkit
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class RoomPoolView (val roomPool : RoomPool, toolkit : Toolkit) : AView2() {

    private val roomViews = MutableList(BoardDomain.COUNT_WAITING_ROOMS) { RoomView(roomPool.waitingRooms[it], toolkit) }

    init {
        roomPool.waitingRooms.onAdd += { _, room ->
            roomViews.removeAt(0)
            roomViews.add(RoomView(room, toolkit))
        }
    }

    override fun render(batch: SpriteBatch, camera: Camera) {
        roomViews.forEach { it.renderRoomElements(batch, camera) }
        roomViews.forEach { it.renderClearables(batch, camera) }
        roomViews.forEach { it.renderWalls(batch, camera) }
        batch.color = Color.WHITE
    }
}