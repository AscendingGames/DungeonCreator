package com.ascending.games.id1.view

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
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

        val buttonStyle = TextButton.TextButtonStyle()
        buttonStyle.down = skin.newDrawable("white", Color.RED)
        buttonStyle.up = skin.newDrawable("white", Color.WHITE)
        buttonStyle.disabled = skin.newDrawable("white", Color.GRAY)
        buttonStyle.font = skin.getFont("default")
        skin.add("default", buttonStyle)
        return skin
    }
}