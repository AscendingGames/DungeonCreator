package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.data.ObservableList
import com.ascending.games.lib.model.geometry.IPosition2
import com.badlogic.gdx.math.Vector2

class Room(val roomElements : ObservableList<RoomElement> = ObservableList(mutableListOf()), override var position : Vector2 = Vector2.Zero.cpy(), var type : RoomType = RoomType.NORMAL) : IPosition2 {
    var isVisited = false

    val allRoomClearables : List<AClearable>
        get() = roomElements.flatMap { it.clearables }
    val allWalls : List<Wall>
        get() = roomElements.flatMap { it.walls }
    val isCleared : Boolean
        get() = isVisited && allRoomClearables.isEmpty()

    init {
        roomElements.forEach { it.room = this }
    }

    fun rotate() : Room {
        roomElements.forEach { it.rotate() }
        return this
    }
}