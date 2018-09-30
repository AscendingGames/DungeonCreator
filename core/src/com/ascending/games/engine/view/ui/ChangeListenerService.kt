package com.ascending.games.engine.view.ui

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class ChangeListenerService {
    fun createChangeListener(callback : () -> Unit) : ChangeListener {
        return object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    callback()
                    event.handle()
                }
            }
        }
    }
}