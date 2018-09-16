package com.ascending.games.id1.view.mechanics

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.view.world.WorldScreen
import com.ascending.games.lib.model.data.ObservableMap
import com.ascending.games.lib.model.game.IStatType
import com.ascending.games.lib.model.game.IStats
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align

class StatsView(private val stats : ObservableMap<IStatType, Float>, stage : Stage, skin : Skin) {
    private val statsTable = Table()

    private var hpLabel : Label
    private var attackLabel : Label
    private var defenseLabel : Label

    init {
        statsTable.setFillParent(true)
        statsTable.align(Align.top)
        stage.addActor(statsTable)

        hpLabel = Label("", skin)
        attackLabel = Label("", skin)
        defenseLabel = Label("", skin)

        statsTable.add(hpLabel).pad(50f)
        statsTable.add(attackLabel).pad(50f)
        statsTable.add(defenseLabel).pad(50f)

        updateLabels()

        stats.onChange += { _, _ -> updateLabels() }
    }

    private fun updateLabels() {
        hpLabel.setText((stats[StatType.CURRENT_HP] ?: 0f).toInt().toString() + "/" + (stats[StatType.MAX_HP] ?: 0f).toInt().toString())
        attackLabel.setText((stats[StatType.ATTACK] ?: 0f).toInt().toString())
        defenseLabel.setText((stats[StatType.DEFENSE] ?: 0f).toInt().toString())
    }
}