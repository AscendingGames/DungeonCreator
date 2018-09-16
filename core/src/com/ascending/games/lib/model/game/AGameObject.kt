package com.ascending.games.lib.model.game

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.lib.model.data.ObservableList
import com.badlogic.gdx.math.Vector2

abstract class AGameObject : IPosition2, IChild, IStats {
    override var relativePosition = Vector2()
    override val absolutePosition: Vector2
        get() =  (parent?.let { if (it is IPosition2) it.absolutePosition else Vector2() } ?: Vector2()).add(relativePosition)
    override val stats = HashMap<IStatType, Float>()
    override val statModifiers  by lazy {
        val statModifiers = ObservableList(mutableListOf<IStatModifier>())
        statModifiers.onAdd += { _, modifier -> modifier.apply(this) }
        statModifiers.onRemove += { it.unapply() }
        statModifiers
    }
}