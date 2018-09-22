package com.ascending.games.id1.model.world

import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.lib.model.data.ObservableMap
import com.ascending.games.lib.model.game.IStatType

class Player {
    val stats = ObservableMap(HashMap<IStatType, Float>())
    var depth = 1
    val enabledRituals = mutableSetOf<Ritual>()
    val performedRituals = mutableSetOf<Ritual>()

    val newDepths : Int
        get() = depth
    val knownDepths : Int
        get() = if (newDepths > 1) newDepths - 1 else 1
}