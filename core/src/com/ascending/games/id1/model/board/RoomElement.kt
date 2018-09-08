package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2

data class RoomElement(var position : Coord2) {
    lateinit var room : Room
}