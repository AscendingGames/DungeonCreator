package com.ascending.games.id1.model.world

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType
import org.junit.Assert.*
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
        playerService.clearLevel(player, hero, 1)
        assertEquals(2, player.depth)
    }

    @Test
    fun performRitual() {
        player.enabledRituals.add(Ritual.RitualOfBlessings.name)
        playerService.performRitual(player, Ritual.RitualOfBlessings)
        assertTrue(player.enabledBlessings.contains(Blessing.Novice.name))
        assertTrue(player.enabledRituals.isEmpty())
        assertTrue(player.performedRituals.contains(Ritual.RitualOfBlessings.name))
    }

    @Test
    fun grantBlessing() {
        player.enabledBlessings.add(Blessing.Novice.name)
        playerService.grantBlessing(player, Blessing.Novice)
        assertTrue(player.enabledBlessings.contains(Blessing.Novice.name))
        assertEquals(player.grantedBlessings[Blessing.Novice.name], 1)
        assertEquals(1, player.enabledBlessings.size)
        assertEquals(PlayerService.INIT_HP + Blessing.Novice.value, player.stats[StatType.MAX_HP.name])

        player.grantedBlessings[Blessing.Novice.name] = Blessing.Novice.maxNumber - 1
        playerService.grantBlessing(player, Blessing.Novice)
        assertFalse(player.enabledBlessings.contains(Blessing.Novice.name))
        assertTrue(Blessing.Novice.isCleared(player.grantedBlessings[Blessing.Novice.name]!!))
        assertTrue(player.enabledBlessings.contains(Blessing.Warrior.name))
    }
}