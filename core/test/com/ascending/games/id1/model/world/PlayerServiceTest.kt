package com.ascending.games.id1.model.world

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlayerServiceTest {

    val playerService = PlayerService()
    val player = playerService.createInitialPlayer()

    @Test
    fun clearLevel() {
        val hero = Hero()
        hero.stats[StatType.MAX_HP.name] = 1f
        playerService.clearLevel(player, hero, 1)
        assertEquals(2, player.depth)
        assertEquals(1f, player.stats[StatType.MAX_HP.name])
        assertTrue(player.enabledRituals.contains(Ritual.RitualOfBlessings.name))
        assertEquals(1, player.enabledRituals.size)
        assertEquals(PlayerService.REWARD_BOARD_CLEAR + PlayerService.INIT_GOLD, player.stats[StatType.GOLD.name])
        playerService.clearLevel(player, hero, 1)
        assertEquals(2, player.depth)
    }
}