package com.ascending.games.lib.view

abstract class AView2(override val batchID: Int = 0) : IView2 {
    override fun dispose() = Unit
}