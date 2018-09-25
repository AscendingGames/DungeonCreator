package com.ascending.games.lib.model.game

import com.ascending.games.lib.model.data.ObservableList
import com.ascending.games.lib.model.data.ObservableMap
import com.ascending.games.lib.model.geometry.IHierarchical2
import com.ascending.games.lib.model.geometry.IRectangle2
import com.badlogic.gdx.math.Vector2

abstract class AGameObject : IRectangle2, IHierarchical2, IStats {
    override var relativePosition = Vector2()
    override var size = Vector2()
    override val stats = ObservableMap(HashMap<String, Float>())
    override val statModifiers  by lazy {
        val statModifiers = ObservableList(mutableListOf<IStatModifier>())
        statModifiers.onAdd += { _, modifier -> modifier.apply(this) }
        statModifiers.onRemove += { it.unapply() }
        statModifiers
    }
}