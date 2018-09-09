package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Direction4

data class Wall(val roomElement : RoomElement, val direction : Direction4, var wallState : WallState) {
}