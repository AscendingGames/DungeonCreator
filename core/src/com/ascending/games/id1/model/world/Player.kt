package com.ascending.games.id1.model.world

import com.ascending.games.lib.model.data.ObservableMap
import com.ascending.games.lib.model.game.IStatType

class Player {
    val stats = ObservableMap(HashMap<IStatType, Float>())
}