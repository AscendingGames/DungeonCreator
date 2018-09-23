package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import org.junit.Assert.*
import org.junit.Test

class GrantBlessingActionTest {

    val player = Player()
    val action = GrantBlessingAction(player, Blessing.Novice)

    @Test
    fun getCanExecute() {
        assertFalse(action.canExecute)
        player.enabledBlessings.add(Blessing.Novice.name)
        assertFalse(action.canExecute)
        player.stats[StatType.GOLD.name] = Blessing.Novice.costs.toFloat()
        assertTrue(action.canExecute)
    }

    @Test
    fun execute() {
        player.stats[StatType.GOLD.name] = Ritual.RitualOfBlessings.goldCosts.toFloat()
        player.enabledBlessings.add(Blessing.Novice.name)
        action.execute()
        assertTrue(player.enabledBlessings.contains(Blessing.Novice.name))
        assertEquals(player.grantedBlessings[Blessing.Novice.name], 1)
        assertEquals(1, player.enabledBlessings.size)
        assertEquals(Blessing.Novice.value, player.stats[StatType.MAX_HP.name])
        assertEquals(0f, player.stats[StatType.GOLD.name])

        player.grantedBlessings[Blessing.Novice.name] = Blessing.Novice.maxNumber - 1
        action.execute()
        assertFalse(player.enabledBlessings.contains(Blessing.Novice.name))
        assertTrue(Blessing.Novice.isCleared(player.grantedBlessings[Blessing.Novice.name]!!))
        assertTrue(player.enabledBlessings.contains(Blessing.Warrior.name))
    }
}