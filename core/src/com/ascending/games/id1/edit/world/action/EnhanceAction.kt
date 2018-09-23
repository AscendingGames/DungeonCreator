package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.lib.edit.action.IAction

class EnhanceAction(private val player : Player, val enhancedStat : StatType, val enhancementLevelStat : StatType, val costsPerLevel : Float, val enchancement : Float = 1.0f) : IAction {
    companion object {
        fun createEnhanceWeaponAction(player : Player) : EnhanceAction {
            return EnhanceAction(player, StatType.ATTACK, StatType.WEAPON_LEVEL, PlayerService.COST_PER_WEAPON_LEVEL)
        }

        fun createEnhanceArmorAction(player : Player) : EnhanceAction {
            return EnhanceAction(player, StatType.DEFENSE, StatType.ARMOR_LEVEL, PlayerService.COST_PER_ARMOR_LEVEL)
        }

        fun createEnhancePotionAction(player : Player) : EnhanceAction {
            return EnhanceAction(player, StatType.HP_PER_POTION, StatType.POTION_LEVEL, PlayerService.COST_PER_POTION_LEVEL, PlayerService.INIT_HP_PER_POTION)
        }

        fun createEnhanceMedicinePouchAction(player : Player) : EnhanceAction {
            return EnhanceAction(player, StatType.MAX_POTIONS, StatType.MEDICINE_PUCH_LEVEL, PlayerService.COST_PER_MEDICINE_POUCH_LEVEL)
        }
    }

    private val playerService = PlayerService()

    private fun hasPlayerHasEnoughGold() : Boolean {
        val gold = (player.stats[StatType.GOLD.name] ?: 0f)
        val neededGold = playerService.getEnhancementCosts(player, enhancementLevelStat, costsPerLevel)
        return gold >= neededGold
    }

    override val canExecute: Boolean
        get() = hasPlayerHasEnoughGold()

    override fun execute(): Boolean {
        player.stats[enhancedStat.name] = (player.stats[enhancedStat.name] ?: 0f) + enchancement
        val enhancementCosts =  playerService.getEnhancementCosts(player, enhancementLevelStat, costsPerLevel)
        player.stats[StatType.GOLD.name] = (player.stats[StatType.GOLD.name] ?: 0f) - enhancementCosts
        player.stats[enhancementLevelStat.name] = (player.stats[enhancementLevelStat.name] ?: 0f) + 1f
        return true
    }
}