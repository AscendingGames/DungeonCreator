package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.lib.view.IVisible
import com.ascending.games.lib.view.ui.ChangeListenerService
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align

abstract class ALocationView(private val worldScreen: WorldScreen, locationDescription : String) : IVisible {
    protected val player = PlayerService().getPlayer(worldScreen.game.saveResource)
    protected val skin = worldScreen.game.skin

    protected val locationTable = Table()
    protected val descriptionLabel = Label(locationDescription, skin)

    private val listenerService = ChangeListenerService()

    init {
        locationTable.setFillParent(true)
        locationTable.align(Align.top)
        locationTable.pad(100f)

        descriptionLabel.setWrap(true)
        descriptionLabel.setAlignment(Align.center or Align.top)

        locationTable.add(descriptionLabel)
        locationTable.row().pad(100f)
    }

    override fun show() {
        worldScreen.game.saveResource.save()
        worldScreen.uiStage.addActor(locationTable)
    }

    override fun hide() {
        locationTable.remove()
    }

    protected fun createChangeListener(callback : (WorldScreen) -> Unit) : ChangeListener {
        return listenerService.createChangeListener { callback(worldScreen) }
    }

    protected fun createTextButton(text : String = "", styleName : String = "default", callback : (WorldScreen) -> Unit) : TextButton {
        val backButton = TextButton(text, skin, styleName)
        backButton.listeners.add(createChangeListener(callback))
        return backButton
    }

    protected fun createBackButton(location : Location = Location.OVERWORLD)  : TextButton {
        return createTextButton("Back") { it.setLocation(location) }
    }

}