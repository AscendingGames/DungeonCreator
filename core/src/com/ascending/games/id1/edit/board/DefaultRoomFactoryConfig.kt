package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.RoomShape
import com.ascending.games.id1.model.board.RoomType
import com.ascending.games.lib.model.geometry.Coord2

data class DefaultRoomFactoryConfig(
        val roomShapes : List<RoomShape>,
        val numberDoors : IntRange = 0..0,
        val numberMonsters : IntRange = 0..0,
        val probHealingCrystal : Float = 0f,
        val minRoomsTillStairsDown : Int = 0,
        val probStairsDown : Float = 0f,
        val roomTypePriorites : Map<RoomType, Int> = emptyMap(),
        val roomTypeMinLevels : Map<RoomType, Int> = emptyMap()) {

    companion object {
        private val DEFAULT_NUMBER_DOORS = 2..4
        private val DEFAULT_NUMBER_MONSTERS = 0..3
        private const val DEFAULT_PROB_CRYSTAL = 0.1f
        private const val DEFAULT_MIN_ROOMS_TILL_STAIRS_DOWN = 25
        private const val DEFAULT_PROB_STAIRS_DOWN = 0.2f

        private val DEFAULT_ROOM_TYPE_PRIORITIES = mapOf<RoomType, Int>(
            RoomType.NORMAL to 75,
            RoomType.DANGER_HIGH to 10,
            RoomType.BOSS to 5,
            RoomType.TREASURY_HIGH to 10
        )

        private val DEFAULT_ROOM_TYPE_MIN_LEVELS = mapOf<RoomType, Int>(
                RoomType.NORMAL to 0,
                RoomType.DANGER_HIGH to 1,
                RoomType.BOSS to 7,
                RoomType.TREASURY_HIGH to 2
        )

        val SHAPE_LINE = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0)))
        val SHAPE_L = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0), Coord2(1,-1)))
        val SHAPE_Z = RoomShape(listOf(Coord2(0,0), Coord2(0,-1), Coord2(-1,-1), Coord2(1,0)))
        val SHAPE_HALF_CROSS = RoomShape(listOf(Coord2(0,0), Coord2(0,-1), Coord2(-1,0), Coord2(1,0)))
        val SHAPE_THUMB = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0), Coord2(1,-1), Coord2(0,-1)))
        val DEFAULT_SHAPES = listOf<RoomShape>(SHAPE_LINE, SHAPE_L, SHAPE_Z, SHAPE_HALF_CROSS, SHAPE_THUMB)

        fun createDefaultRoomFactoryConfig() : DefaultRoomFactoryConfig {
            return DefaultRoomFactoryConfig(
                    DEFAULT_SHAPES,
                    DEFAULT_NUMBER_DOORS,
                    DEFAULT_NUMBER_MONSTERS,
                    DEFAULT_PROB_CRYSTAL,
                    DEFAULT_MIN_ROOMS_TILL_STAIRS_DOWN,
                    DEFAULT_PROB_STAIRS_DOWN,
                    DEFAULT_ROOM_TYPE_PRIORITIES,
                    DEFAULT_ROOM_TYPE_MIN_LEVELS
            )
        }
    }
}