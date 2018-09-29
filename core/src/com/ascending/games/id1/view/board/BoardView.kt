package com.ascending.games.id1.view.board

import com.ascending.games.id1.model.board.Board
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.view.AView2
import com.ascending.games.lib.view.SpriteView
import com.ascending.games.lib.view.Toolkit
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class BoardView(val board : Board, val toolkit : Toolkit) : AView2(0) {

    companion object  {
        val OFFSET : Coord2 = Coord2(1, 2)
        const val TILE_SIZE : Float = 50f
        val BOARD_COLOR : Color = Color.GRAY
        const val BOARD_LINE_SIZE = 10f

        fun convertToScreenCoordinates(coord : Coord2) : Vector2 {
            return Vector2((OFFSET.x + coord.x) * TILE_SIZE, (OFFSET.y + coord.y) * TILE_SIZE)
        }

        fun convertToScreenCoordinates(vector : Vector2) : Vector2 {
            return Vector2((OFFSET.x + vector.x) * TILE_SIZE, (OFFSET.y + vector.y) * TILE_SIZE)
        }
    }

    val shapeRenderer = ShapeRenderer()
    private val boardArea = Rectangle(OFFSET.x * TILE_SIZE, OFFSET.y * TILE_SIZE, board.width * TILE_SIZE, board.height * TILE_SIZE)
    private val heroView = SpriteView(BoardRectangle(board.hero), toolkit.textureManager.getTexture("hero.png"), 0)
    private val roomViews = board.rooms.asSequence().map { RoomView(it, shapeRenderer) }.toMutableList()

    init {
        board.rooms.onAdd += { _, room -> roomViews.add(RoomView(room, shapeRenderer)) }
        board.rooms.onRemove += { room -> roomViews.removeAt(roomViews.indexOfFirst { it.room == room }) }
    }

    override fun render(batch: SpriteBatch, camera : Camera) {
        batch.end()
        shapeRenderer.projectionMatrix = camera.combined
        Gdx.gl.glLineWidth(BOARD_LINE_SIZE)

        roomViews.forEach { it.render(batch, camera) }
        if (board.hero.spawned) {
            batch.begin()
            heroView.render(batch, camera)
            batch.end()
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.setColor(BOARD_COLOR.r, BOARD_COLOR.g, BOARD_COLOR.b, BOARD_COLOR.a)
        shapeRenderer.rect(boardArea.x, boardArea.y, boardArea.width, boardArea.height)
        shapeRenderer.end()

        Gdx.gl.glLineWidth(1f)
        batch.begin()
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}