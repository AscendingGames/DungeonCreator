package com.ascending.games.id1.model.world

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType

class PlayerService {

    companion object {
        const val INIT_HP = 10f
        const val INIT_ATTACK = 1f
        const val INIT_DEFENSE = 0f
        const val INIT_SPEED = 2f
        const val INIT_LEVEL = 1f
        const val INIT_GOLD = 1000f
    }

    fun createInitialPlayer() : Player {
        val initialPlayer = Player()
        initialPlayer.stats[StatType.MAX_HP.name] = INIT_HP
        initialPlayer.stats[StatType.CURRENT_HP.name] = INIT_HP
        initialPlayer.stats[StatType.ATTACK.name] = INIT_ATTACK
        initialPlayer.stats[StatType.DEFENSE.name] = INIT_DEFENSE
        initialPlayer.stats[StatType.SPEED.name] = INIT_SPEED
        initialPlayer.stats[StatType.LEVEL.name] = INIT_LEVEL
        initialPlayer.stats[StatType.GOLD.name] = INIT_GOLD
        return initialPlayer
    }

    fun clearLevel(player : Player, hero : Hero, depth : Int) {
        hero.stats.forEach { statType, value -> if (StatType.valueOf(statType).isPermanentStat) player.stats.put(statType, value) }
        player.stats[StatType.CURRENT_HP.name] = player.stats[StatType.MAX_HP.name] ?: 0f
        if (depth == player.depth) {
            advanceDepth(player)
        }
    }

    fun advanceDepth(player : Player) {
        player.depth++
        updateEnabledRituals(player)
    }

    fun updateEnabledRituals(player : Player) {
        player.enabledRituals += Ritual.values().filter { player.depth >= it.unlockDepth && !player.performedRituals.contains(it.name) }.map { it.name }
    }

    fun getClearedBlessings(player : Player) : Set<Blessing> {
        return player.grantedBlessings.mapKeys { entry -> Blessing.valueOf(entry.key) }.filter { (blessing, level) -> blessing.isCleared(level) }.keys
    }

    fun updateEnabledBlessings(player : Player) {
        player.enabledBlessings += Blessing.values().filter { !player.grantedBlessings.keys.contains(it.name) }.filter { getClearedBlessings(player).containsAll(it.requires) }.map { it.name }
    }

    fun performRitual(player : Player, ritual : Ritual) {
        with(player) {
            performedRituals.add(ritual.name)
            enabledRituals.remove(ritual.name)
            stats[StatType.GOLD.name] = (stats[StatType.GOLD.name] ?: 0f) - ritual.goldCosts.toFloat()
            stats[StatType.MAX_HP.name] = (stats[StatType.MAX_HP.name] ?: 0f) - ritual.hpCosts.toFloat()
            stats[StatType.CURRENT_HP.name] = (stats[StatType.CURRENT_HP.name] ?: 0f) - ritual.hpCosts.toFloat()
        }

        updateEnabledBlessings(player)
    }

    fun isRitualEnabled(player : Player, ritual : Ritual) : Boolean {
        return player.enabledRituals.contains(ritual.name)
                && (player.stats[StatType.GOLD.name] ?: 0f) >= ritual.goldCosts.toFloat()
                && (player.stats[StatType.MAX_HP.name] ?: 0f) >= ritual.hpCosts.toFloat()
    }

    fun grantBlessing(player : Player, blessing : Blessing) {
        with(player) {
            val newLevel = (grantedBlessings[blessing.name] ?: 0) + 1
            grantedBlessings.put(blessing.name, newLevel)
            if (blessing.isCleared(newLevel)) enabledBlessings.remove(blessing.name)
            stats[StatType.GOLD.name] = (stats[StatType.GOLD.name] ?: 0f) - blessing.costs.toFloat()
            stats[blessing.stat.name] = (stats[blessing.stat.name] ?: 0f) + blessing.value
        }

        updateEnabledBlessings(player)
    }

    fun isBlessingEnabled(player : Player, blessing : Blessing) : Boolean {
        return player.enabledBlessings.contains(blessing.name)
                && (player.stats[StatType.GOLD.name] ?: 0f) >= blessing.costs.toFloat()
    }
}