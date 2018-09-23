package com.ascending.games.id1.model.world

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.lib.edit.resource.IResource

class PlayerService {

    companion object {
        const val INIT_HP = 10f
        const val INIT_ATTACK = 1f
        const val INIT_DEFENSE = 0f
        const val INIT_SPEED = 2f
        const val INIT_LEVEL = 1f
        const val INIT_GOLD = 100f
        const val INIT_HP_PER_POTION = 20f
        const val COST_PER_POTION = 100f
        const val COST_INCREASE_PER_POTION_LEVEL = 50f
        const val COST_PER_WEAPON_LEVEL = 500f
        const val COST_PER_ARMOR_LEVEL = 1000f
        const val COST_PER_POTION_LEVEL = 500f
        const val COST_PER_MEDICINE_POUCH_LEVEL = 1000f
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
        initialPlayer.stats[StatType.HP_PER_POTION.name] = INIT_HP_PER_POTION
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

    fun getPlayer(resource : IResource) : Player {
        return resource.contents[0] as Player
    }

    fun getEnhancementCosts(player : Player, enhancementLevelStat : StatType, costsPerLevel : Float) : Float {
        return ((player.stats[enhancementLevelStat.name] ?: 0f) + 1f) * costsPerLevel
    }

    fun getPotionCosts(player : Player) : Float {
        return COST_PER_POTION + (player.stats[StatType.POTION_LEVEL.name] ?: 0f) * COST_INCREASE_PER_POTION_LEVEL
    }
}