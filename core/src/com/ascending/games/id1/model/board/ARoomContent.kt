package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.game.AGameObject

abstract class ARoomContent : AGameObject() {
    override val parent: Any?
        get() = roomElement

    lateinit var roomElement : RoomElement
}