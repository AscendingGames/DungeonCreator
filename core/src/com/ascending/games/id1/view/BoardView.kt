package com.ascending.games.id1.view

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.WallState
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.view.AView2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class BoardView(val board : Board) : AView2(0) {

    companion object  {
        val OFFSET : Coord2 = Coord2(2, 2)
        const val TILE_SIZE : Float = 50f
        val BOARD_COLOR : Color = Color.GRAY
        const val BOARD_LINE_SIZE = 10f
        const val WALL_LINE_SIZE = 3f
    }

    private val shapeRenderer = ShapeRenderer()
    private val boardArea = Rectangle(OFFSET.x * TILE_SIZE, OFFSET.y * TILE_SIZE, board.width * TILE_SIZE, board.height * TILE_SIZE)
    private val heroView = HeroView(board.hero, shapeRenderer)
    private val roomViews = board.rooms.map { RoomView(it, shapeRenderer) }.toMutableList()

    init {
        board.rooms.onAdd += { index, room -> roomViews.add(RoomView(room, shapeRenderer)) }
        board.rooms.onRemove += { room -> roomViews.removeAt(roomViews.indexOfFirst { it.room == room }) }
    }

    override fun render(batch: SpriteBatch, camera : Camera) {
        shapeRenderer.projectionMatrix = camera.combined
        Gdx.gl.glLineWidth(BOARD_LINE_SIZE)

        roomViews.forEach { it.render(batch, camera) }
        heroView.render(batch, camera)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.setColor(BOARD_COLOR.r, BOARD_COLOR.g, BOARD_COLOR.b, BOARD_COLOR.a)
        shapeRenderer.rect(boardArea.x, boardArea.y, boardArea.width, boardArea.height)
        shapeRenderer.end()
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}