package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.RoomType
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultRoomFactoryConfigTest {
    @Test
    fun createDefaultRoomFactoryConfigTest() {
        val defaultRoomFactoryConfig = DefaultRoomFactoryConfig.createDefaultRoomFactoryConfig()
        val toBeDefinedRoomTypes = RoomType.values().filter { it != RoomType.TREASURY && it != RoomType.DANGER }.toSet()
        assertEquals(toBeDefinedRoomTypes, defaultRoomFactoryConfig.roomTypeMinLevels.keys)
        assertEquals(toBeDefinedRoomTypes, defaultRoomFactoryConfig.roomTypePriorities.keys)
    }
}