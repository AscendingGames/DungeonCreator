package com.ascending.games.id1.view.mechanics

import com.ascending.games.id1.model.mechanics.StatService
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.engine.model.data.ObservableMap
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align

class StatsView(private val stats : ObservableMap<String, Float>, stage : Stage, skin : Skin) {
    private val statsTable = Table()

    private val hpLabel = Label("", skin)
    private val hpImage = Image(skin, "hp")
    private val attackLabel = Label("", skin)
    private val attackImage = Image(skin, "sword")
    private val defenseLabel = Label("", skin)
    private val defenseImage = Image(skin, "shield")
    private val potionsLabel = Label("", skin)
    private val potionsImage = Image(skin, "potion")
    private val levelLabel = Label("", skin)
    private val expLabel = Label("", skin)
    private val goldLabel = Label("", skin)
    private val goldImage = Image(skin, "gold")

    init {
        statsTable.setFillParent(true)
        statsTable.align(Align.top)
        stage.addActor(statsTable)

        statsTable.add(hpLabel).padLeft(40f).padTop(30f).padBottom(30f)
        statsTable.add(hpImage).prefSize(attackLabel.prefHeight).padRight(40f)
        statsTable.add(attackLabel)
        statsTable.add(attackImage).prefSize(attackLabel.prefHeight).padRight(40f)
        statsTable.add(defenseLabel)
        statsTable.add(defenseImage).prefSize(attackLabel.prefHeight).padRight(40f)
        statsTable.add(potionsLabel)
        statsTable.add(potionsImage).prefSize(attackLabel.prefHeight).padRight(40f)
        statsTable.add(levelLabel).padRight(40f)
        statsTable.add(expLabel).padRight(40f)
        statsTable.add(goldLabel)
        statsTable.add(goldImage).prefSize(attackLabel.prefHeight).padRight(40f)

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