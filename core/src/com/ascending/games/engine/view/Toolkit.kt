package com.ascending.games.engine.view

import com.ascending.games.engine.view.texture.TextureManager
import com.badlogic.gdx.math.Vector2

class Toolkit(viewportSize : Vector2) {
    val sceneManager = SceneManager2(viewportSize)
    val textureManager = TextureManager()

    fun dispose() {
        sceneManager.dispose()
        textureManager.dispose()
    }
}