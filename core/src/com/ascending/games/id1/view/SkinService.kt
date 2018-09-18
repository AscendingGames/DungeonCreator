package com.ascending.games.id1.view

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class SkinService {
    fun createSkin() : Skin {
        val skin = Skin()
        // Generate a 1x1 white texture and store it in the skin named "white".
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        skin.add("white", Texture(pixmap))

        // Store the default libgdx font under the name "default".
        skin.add("default", BitmapFont())
        skin.add("default", Color.WHITE)

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
        skin.add("overworld", overworldTextButtonStyle)

        val defaultTextButtonStyle = TextButton.TextButtonStyle()
        defaultTextButtonStyle.down = skin.newDrawable("white", Color.RED)
        defaultTextButtonStyle.up = skin.newDrawable("white", Color.WHITE)
        defaultTextButtonStyle.disabled = skin.newDrawable("white", Color.GRAY)
        defaultTextButtonStyle.font = skin.getFont("default")
        skin.add("default", defaultTextButtonStyle)
        return skin
    }
}