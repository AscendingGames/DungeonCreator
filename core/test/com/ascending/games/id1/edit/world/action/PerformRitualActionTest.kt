package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import org.junit.Assert.*
import org.junit.Test

class PerformRitualActionTest {

    val player = Player()
    val action = PerformRitualAction(player, Ritual.RitualOfBlessings)

    @Test
    fun getCanExecute() {
        assertFalse(action.canExecute)
        player.enabledRituals.add(Ritual.RitualOfBlessings.name)
        assertFalse(action.canExecute)
        player.stats[StatType.GOLD.name] = Ritual.RitualOfBlessings.goldCosts.toFloat()
        assertFalse(action.canExecute)
        player.stats[StatType.MAX_HP.name] = Ritual.RitualOfBlessings.hpCosts.toFloat()
        assertFalse(action.canExecute)
        player.stats[StatType.MAX_HP.name] = Ritual.RitualOfBlessings.hpCosts.toFloat() + 1
        assertTrue(action.canExecute)
    }

    @Test
    fun execute() {
        player.stats[StatType.GOLD.name] = Ritual.RitualOfBlessings.goldCosts.toFloat()
        player.stats[StatType.MAX_HP.name] = Ritual.RitualOfBlessings.hpCosts.toFloat() + 1
        player.enabledRituals.add(Ritual.RitualOfBlessings.name)
        action.execute()
        assertTrue(player.enabledBlessings.contains(Blessing.Novice.name))
        assertTrue(player.enabledRituals.isEmpty())
        assertTrue(player.performedRituals.contains(Ritual.RitualOfBlessings.name))
        assertEquals(0f, player.stats[StatType.GOLD.name])
        assertEquals(1f, player.stats[StatType.MAX_HP.name])
    }
}