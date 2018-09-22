package com.ascending.games.id1.model.world

import com.ascending.games.lib.model.data.ObservableMap

class Player {
    val stats = ObservableMap<String, Float>()
    var depth = 1
    val enabledRituals = mutableSetOf<String>()
    val performedRituals = mutableSetOf<String>()

    val enabledBlessings = mutableSetOf<String>()
    val grantedBlessings = mutableMapOf<String, Int>()

    val newDepths : Int
        get() = depth
    val knownDepths : Int
        get() = if (newDepths > 1) newDepths - 1 else 1
}