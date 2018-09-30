package com.ascending.games.engine.view

abstract class AView2(override val batchID: Int = 0) : IView2 {
    override fun dispose() = Unit
}