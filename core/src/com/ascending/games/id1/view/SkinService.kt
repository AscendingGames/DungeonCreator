package com.ascending.games.id1.view

import com.ascending.games.engine.view.texture.TextureManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class SkinService {
    fun createUISkin(textureManager: TextureManager) : Skin {

    }

    fun createUISkin(textureManager : TextureManager) : Skin {
        /*
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
        skin.add("shrine", textureManager.getTexture("shrine.png"))
        skin.add("city", textureManager.getTexture("city.png"))
        skin.add("dungeon", textureManager.getTexture("dungeon.png"))
        skin.add("shrine_down", textureManager.getTexture("shrineclick.png"))
        skin.add("city_down", textureManager.getTexture("cityclick.png"))
        skin.add("dungeon_down", textureManager.getTexture("dungeonclick.png"))

        // Store the default libgdx font under the name "default".
        skin.add("default", BitmapFont())
        skin.add("default", Color.BLACK)

        val labelStyle = Label.LabelStyle()
        labelStyle.font = skin.getFont("default")
        labelStyle.fontColor = skin.getColor("default")
        skin.add("default", labelStyle)

        val cityTextButtonStyle = TextButton.TextButtonStyle()
        cityTextButtonStyle.down = skin.newDrawable("city_down", Color.WHITE)
        cityTextButtonStyle.up = skin.newDrawable("city", Color.WHITE)
        cityTextButtonStyle.disabled = skin.newDrawable("city", Color.GRAY)
        cityTextButtonStyle.font = skin.getFont("default")
        cityTextButtonStyle.unpressedOffsetY = 150f
        cityTextButtonStyle.pressedOffsetY = 150f
        cityTextButtonStyle.fontColor = skin.getColor("default")
        skin.add("city", cityTextButtonStyle)

        val shrineTextButtonStyle = TextButton.TextButtonStyle()
        shrineTextButtonStyle.down = skin.newDrawable("shrine_down", Color.WHITE)
        shrineTextButtonStyle.up = skin.newDrawable("shrine", Color.WHITE)
        shrineTextButtonStyle.disabled = skin.newDrawable("shrine", Color.GRAY)
        shrineTextButtonStyle.font = skin.getFont("default")
        shrineTextButtonStyle.unpressedOffsetY = 50f
        shrineTextButtonStyle.pressedOffsetY = 50f
        shrineTextButtonStyle.fontColor = skin.getColor("default")
        skin.add("shrine", shrineTextButtonStyle)

        val dungeonTextButtonStyle = TextButton.TextButtonStyle()
        dungeonTextButtonStyle.down = skin.newDrawable("dungeon_down", Color.WHITE)
        dungeonTextButtonStyle.up = skin.newDrawable("dungeon", Color.WHITE)
        dungeonTextButtonStyle.disabled = skin.newDrawable("dungeon", Color.GRAY)
        dungeonTextButtonStyle.font = skin.getFont("default")
        dungeonTextButtonStyle.unpressedOffsetY = 40f
        dungeonTextButtonStyle.pressedOffsetY = 40f
        dungeonTextButtonStyle.fontColor = skin.getColor("default")
        skin.add("dungeon", dungeonTextButtonStyle)

        val defaultTextButtonStyle = TextButton.TextButtonStyle()
        defaultTextButtonStyle.down = skin.newDrawable("menuItem", Color.RED)
        defaultTextButtonStyle.up = skin.newDrawable("menuItem", Color.WHITE)
        defaultTextButtonStyle.disabled = skin.newDrawable("menuItem", Color.GRAY)
        defaultTextButtonStyle.font = skin.getFont("default")
        defaultTextButtonStyle.fontColor = skin.getColor("default")
        skin.add("default", defaultTextButtonStyle)
        return skin
        */

        return Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"))
    }
}