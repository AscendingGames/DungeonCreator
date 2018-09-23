package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import org.junit.Assert.*
import org.junit.Test

class EnhanceActionTest {

    val player = Player()
    val enhanceAction = EnhanceAction(player, StatType.WEAPON_LEVEL, StatType.ATTACK, 1f)

    @Test
    fun getCanExecute() {
        assertFalse(enhanceAction.canExecute)
        player.stats[StatType.GOLD.name] = 1f
        assertTrue(enhanceAction.canExecute)
    }

    @Test
    fun execute() {
        player.stats[StatType.GOLD.name] = 1f
        enhanceAction.execute()
        assertEquals(1f, player.stats[StatType.WEAPON_LEVEL.name])
        assertEquals(1f, player.stats[StatType.ATTACK.name])
        assertEquals(0f, player.stats[StatType.GOLD.name])
    }

    @Test
    fun testCreateEnhanceWeaponAction() {
        val createdAction = EnhanceAction.createEnhanceWeaponAction(player)
        assertEquals(StatType.WEAPON_LEVEL, createdAction.enhancementLevelStat)
        assertEquals(StatType.ATTACK, createdAction.enhancedStat)
        assertEquals(PlayerService.COST_PER_WEAPON_LEVEL, createdAction.costsPerLevel)
    }

    @Test
    fun testCreateEnhanceArmorAction() {
        val createdAction = EnhanceAction.createEnhanceArmorAction(player)
        assertEquals(StatType.ARMOR_LEVEL, createdAction.enhancementLevelStat)
        assertEquals(StatType.DEFENSE, createdAction.enhancedStat)
        assertEquals(PlayerService.COST_PER_ARMOR_LEVEL, createdAction.costsPerLevel)
    }

    @Test
    fun testCreateEnhancePotionsAction() {
        val createdAction = EnhanceAction.createEnhancePotionAction(player)
        assertEquals(StatType.POTION_LEVEL, createdAction.enhancementLevelStat)
        assertEquals(StatType.HP_PER_POTION, createdAction.enhancedStat)
        assertEquals(PlayerService.COST_PER_POTION_LEVEL, createdAction.costsPerLevel)
    }

    @Test
    fun testCreateEnhanceMedicinePouchAction() {
        val createdAction = EnhanceAction.createEnhanceMedicinePouchAction(player)
        assertEquals(StatType.MEDICINE_PUCH_LEVEL, createdAction.enhancementLevelStat)
        assertEquals(StatType.MAX_POTIONS, createdAction.enhancedStat)
        assertEquals(PlayerService.COST_PER_MEDICINE_POUCH_LEVEL, createdAction.costsPerLevel)
    }
}