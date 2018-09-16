package com.ascending.games.lib.model.game

interface IChild {
    val parent : Any?
    val isRoot : Boolean
            get() = parent == null
}