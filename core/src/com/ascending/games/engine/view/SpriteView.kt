package com.ascending.games.engine.view

import com.ascending.games.engine.model.geometry.IRectangle2
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SpriteView(val rectangleProvider : IRectangle2, var texture : Texture, batchID : Int = 0) : AView2(batchID) {

    override fun render(batch: SpriteBatch, camera: Camera) {
        val rect = rectangleProvider.rectangle
        batch.draw (texture, rect.x, rect.y, rect.width, rect.height)
    }
}