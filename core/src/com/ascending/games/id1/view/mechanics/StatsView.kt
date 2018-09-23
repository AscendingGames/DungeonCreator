package com.ascending.games.id1.view.mechanics

import com.ascending.games.id1.model.mechanics.StatService
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.lib.model.data.ObservableMap
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align

class StatsView(private val stats : ObservableMap<String, Float>, stage : Stage, skin : Skin) {
    private val statsTable = Table()

    private var hpLabel : Label
    private var attackLabel : Label
    private var defenseLabel : Label
    private var potionsLabel : Label
    private var levelLabel : Label
    private var expLabel : Label
    private var goldLabel : Label

    init {
        statsTable.setFillParent(true)
        statsTable.align(Align.top)
        stage.addActor(statsTable)

        hpLabel = Label("", skin)
        attackLabel = Label("", skin)
        defenseLabel = Label("", skin)
        levelLabel = Label("", skin)
        potionsLabel = Label("", skin)
        expLabel = Label("", skin)
        goldLabel = Label("", skin)

        statsTable.add(hpLabel).pad(30f)
        statsTable.add(attackLabel).pad(30f)
        statsTable.add(defenseLabel).pad(30f)
        statsTable.add(potionsLabel).pad(30f)
        statsTable.add(levelLabel).pad(30f)
        statsTable.add(expLabel).pad(30f)
        statsTable.add(goldLabel).pad(30f)

        updateLabels()

        stats.onChange += { _, _ -> updateLabels() }
    }

    private fun updateLabels() {
        hpLabel.setText((stats[StatType.CURRENT_HP.name] ?: 0f).toInt().toString() + "/" + (stats[StatType.MAX_HP.name] ?: 0f).toInt().toString())
        attackLabel.setText((stats[StatType.ATTACK.name] ?: 0f).toInt().toString())
        defenseLabel.setText((stats[StatType.DEFENSE.name] ?: 0f).toInt().toString())
        potionsLabel.setText((stats[StatType.COUNT_POTIONS.name] ?: 0f).toInt().toString())
        levelLabel.setText("Lvl. " + (stats[StatType.LEVEL.name] ?: 0f).toInt().toString())
        expLabel.setText("Exp. " + (stats[StatType.EXP.name] ?: 0f).toInt().toString() + "/" + (StatService().getNextExp(stats).toInt().toString()))
        goldLabel.setText((stats[StatType.GOLD.name] ?: 0f).toInt().toString())
    }
}