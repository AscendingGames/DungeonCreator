package com.ascending.games.lib.view

import com.ascending.games.lib.view.texture.TextureManager
import com.badlogic.gdx.math.Vector2

class Toolkit(viewportSize : Vector2) {
    val sceneManager = SceneManager2(viewportSize)
    val textureManager = TextureManager()

    fun dispose() {
        sceneManager.dispose()
        textureManager.dispose()
    }
}