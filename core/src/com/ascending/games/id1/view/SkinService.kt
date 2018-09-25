package com.ascending.games.id1.view

import com.ascending.games.lib.view.texture.TextureManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class SkinService {
    fun createSkin(textureManager : TextureManager) : Skin {
        val skin = Skin()
        // Generate a 1x1 white texture and store it in the skin named "white".
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        skin.add("white", Texture(pixmap))
        skin.add("sword", textureManager.getTexture("sword.png"))
        skin.add("shield", textureManager.getTexture("shield.png"))
        skin.add("gold", textureManager.getTexture("money.png"))
        skin.add("hp", textureManager.getTexture("heart.png"))
        skin.add("potion", textureManager.getTexture("potion.png"))
        skin.add("menuItem", textureManager.getTexture("menubg.png"))

        // Store the default libgdx font under the name "default".
        skin.add("default", BitmapFont())
        skin.add("default", Color.BLACK)

        val labelStyle = Label.LabelStyle()
        labelStyle.font = skin.getFont("default")
        labelStyle.fontColor = skin.getColor("default")
        skin.add("default", labelStyle)

        val overworldTextButtonStyle = TextButton.TextButtonStyle()
        overworldTextButtonStyle.down = skin.newDrawable("white", Color.RED)
        overworldTextButtonStyle.up = skin.newDrawable("white", Color.WHITE)
        overworldTextButtonStyle.disabled = skin.newDrawable("white", Color.GRAY)
        overworldTextButtonStyle.font = skin.getFont("default")
        overworldTextButtonStyle.unpressedOffsetY = 20f
        overworldTextButtonStyle.pressedOffsetY = 20f
        overworldTextButtonStyle.fontColor = skin.getColor("default")
        skin.add("overworld", overworldTextButtonStyle)

        val defaultTextButtonStyle = TextButton.TextButtonStyle()
        defaultTextButtonStyle.down = skin.newDrawable("menuItem", Color.RED)
        defaultTextButtonStyle.up = skin.newDrawable("menuItem", Color.WHITE)
        defaultTextButtonStyle.disabled = skin.newDrawable("menuItem", Color.GRAY)
        defaultTextButtonStyle.font = skin.getFont("default")
        defaultTextButtonStyle.fontColor = skin.getColor("default")
        skin.add("default", defaultTextButtonStyle)
        return skin
    }
}