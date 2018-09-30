package com.ascending.games.engine.model.game

interface IChild {
    val parent : Any?
    val isRoot : Boolean
            get() = parent == null
}