package com.ascending.games.id1.view

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.lib.view.AView2
import com.ascending.games.lib.view.IView2
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class HeroView(val hero : Hero, val shapeRenderer : ShapeRenderer) : AView2(0) {
    override fun render(batch: SpriteBatch, camera: Camera) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(Color.GREEN)
        val rectPos = Vector2((hero.absolutePosition.x + BoardView.OFFSET.x) * BoardView.TILE_SIZE, (hero.absolutePosition.y + BoardView.OFFSET.y) * BoardView.TILE_SIZE)
        shapeRenderer.rect(rectPos.x, rectPos.y, BoardView.TILE_SIZE, BoardView.TILE_SIZE)
        shapeRenderer.end()
    }

    override fun dispose() {

    }
}