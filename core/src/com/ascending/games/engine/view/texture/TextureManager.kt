package com.ascending.games.engine.view.texture

import com.badlogic.gdx.graphics.Texture

class TextureManager {
    private val textures = mutableMapOf<String, Texture>()

    fun getTexture(name : String) : Texture {
        var loadedTexture = textures[name]
        if (loadedTexture == null) {
            loadedTexture = Texture(name)
            textures[name] = loadedTexture
        }
        return loadedTexture
    }

    fun dispose() {
        textures.forEach { _, texture -> texture.dispose() }
    }
}