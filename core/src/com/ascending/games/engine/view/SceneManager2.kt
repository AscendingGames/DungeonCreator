package com.ascending.games.engine.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable

class SceneManager2(viewportSize : Vector2) : Disposable {

    val views : MutableList<IView2> = mutableListOf()
    private var batchMap : Map<Int, SpriteBatch>  = mutableMapOf()
    private val camera = OrthographicCamera(viewportSize.x, viewportSize.y)

    init {
        camera.setToOrtho(false)
    }

    private fun getSpriteBatch(id : Int) : SpriteBatch {
        var batch = batchMap[id]
        if (batch == null) {
            batch = SpriteBatch()
            batchMap = batchMap.plus(Pair(id, batch))
        }

        return batch
    }

    fun render() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()

        val viewMap = views.groupBy { it.batchID }
        for (viewBatch in viewMap) {
            val batch = getSpriteBatch(viewBatch.key)
            batch.begin()
            for (view in viewBatch.value) {
                view.render(batch, camera)
            }
            batch.end()
        }
    }

    override fun dispose() {
        for (batch in batchMap.values) {
            batch.dispose()
        }

        batchMap = emptyMap()
    }
}