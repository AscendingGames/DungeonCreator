package com.ascending.games.engine.view

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable

interface IView2 : Disposable {
    val batchID : Int

    fun render(batch: SpriteBatch, camera : Camera)
}