package com.ascending.games.id1.model.board

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.IRoomFactory
import com.ascending.games.engine.model.data.ObservableList
import com.badlogic.gdx.math.Vector2
import java.util.*

class RoomPool(private val roomFactory : IRoomFactory, private val poolPositionY : Float) {
    companion object {
        const val OFFSET_X = 1f
        const val PADDING_X = 4
    }

    val waitingRooms = ObservableList(MutableList(BoardDomain.COUNT_WAITING_ROOMS) { roomFactory.createRoom() })

    init {
        updatePoolPositions()
    }

    private fun updatePoolPositions() {
        for (i in 0 until waitingRooms.size) {
            waitingRooms[i].position = Vector2(OFFSET_X + i * PADDING_X, poolPositionY)
        }
    }

    fun poll() : Room {
        val current = waitingRooms.removeAt(0)
        waitingRooms.add(roomFactory.createRoom())
        updatePoolPositions()
        return current
    }
}