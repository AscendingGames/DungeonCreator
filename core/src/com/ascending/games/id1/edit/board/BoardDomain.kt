package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.content.HeroActionProvider
import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.edit.board.action.room.IBoardAction
import com.ascending.games.id1.model.board.*
import com.ascending.games.id1.model.mechanics.StatService
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.lib.edit.action.ITimedAction
import com.ascending.games.lib.edit.action.ITimedActionProvider
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class BoardDomain(val board: Board, val player : Player, val level : Int, roomFactory : IRoomFactory) {

    val onProjectedRoomChanged = HashSet<() -> Unit>()
    val onBoardFinished = HashSet<(Boolean) -> Unit>()

    companion object {
        const val COUNT_WAITING_ROOMS = 3
    }

    var currentRoom by Delegates.notNull<Room>()
    var projectedRoom by Delegates.notNull<Room>()
    val roomPool = RoomPool(roomFactory, (board.height + 2).toFloat())

    private var time = 0f
    private val heroActionProvider = HeroActionProvider(board)
    private val mapRoomContentToActionList = mutableMapOf<ARoomContent, ITimedAction>()
    private val statService = StatService()
    private val playerService = PlayerService()

    init {
        player.stats.forEach { statType, value -> board.hero.stats.put(statType, value) }
        nextRoom()
    }

    fun failBoard() {
        player.stats.put(StatType.COUNT_POTIONS.name, board.hero.stats[StatType.COUNT_POTIONS.name] ?: 0f)
        onBoardFinished.forEach { it.invoke(false) }
    }

    fun clearBoard() {
        playerService.clearLevel(player, board.hero, level)
        onBoardFinished.forEach { it.invoke(true) }
    }

    fun update(time : Float) {
        updateFallingRooms(time)

        for (row in 0 until board.height) {
            clearRowIfFull(row)
        }

        updateRoomContentActions(time)

        if (board.hero.spawned) {
            if (statService.isDead(board.hero)) {
                failBoard()
            } else if (board.hero.roomElement.roomContents.any { it is StairsDown }) {
                clearBoard()
            }
        }
    }

    private fun updateFallingRooms(time : Float) {
        var positionChanged = false
        for (room in board.rooms) {
            if (!board.hasRoomFallen(room)) {
                room.position.y -= time
                positionChanged = true
            } else {
                room.position.y = room.position.y.roundToInt().toFloat()
                board.openWallsNeighbouringDoors(room)
            }
        }

        if (!positionChanged) {
            if (!board.hero.spawned) {
                board.hero.spawn(board)
                heroActionProvider.lastRoom = board.hero.roomElement.room
            }

            nextRoom()
        }
    }

    private fun updateRoomContentActions(time : Float)  {
        val heroAction = getAction(board.hero, heroActionProvider)

        if (heroAction != null && (!heroAction.canExecute || heroAction.execute(time))) {
            mapRoomContentToActionList.remove(board.hero)
        }
    }

    private fun getAction(aRoomContent: ARoomContent, timedActionProvider : ITimedActionProvider) : ITimedAction? {
        val existingAction = mapRoomContentToActionList[aRoomContent]
        if (existingAction != null)  return existingAction

        val newAction = timedActionProvider.getNextActions()
        if (newAction != null) mapRoomContentToActionList[board.hero] = newAction
        return newAction
    }

    fun execute(action : IBoardAction) {
        action.execute(currentRoom,board)
        updateProjectedRoom()
    }

    private fun getClearedElements(row : Int) : List<RoomElement> {
        var clearedElements = emptyList<RoomElement>()

        for (x in 0 until board.width) {
            val roomElement = board.getRoomElementAt(Coord2(x, row))
            roomElement ?: return emptyList()

            if (!roomElement.room.isCleared) return emptyList()
            if (board.hero.spawned && board.hero.roomElement == roomElement) return emptyList()
            if (!board.hasRoomFallen(roomElement.room)) return emptyList()

            clearedElements += roomElement
        }

        return clearedElements
    }

    fun clearRowIfFull(row : Int) : Boolean {
        val clearedElements = getClearedElements(row)
        for (roomElement in clearedElements) {
            statService.rewardRoomElementClear(board.hero)

            val room = roomElement.room
            room.roomElements -= roomElement
            if (room.roomElements.isEmpty()) {
                board.rooms.remove(room)
            }
        }

        return !clearedElements.isEmpty()
    }

    fun nextRoom() {
        currentRoom = roomPool.poll()
        currentRoom.position = Vector2(((board.width - 1) / 2).toFloat(), board.height.toFloat() - 1)

        board.rooms.add(currentRoom)
        updateProjectedRoom()

        if (board.isRoomOverlapping(currentRoom)) {
            failBoard()
        }
    }

    private fun updateProjectedRoom() {
        val positionY = currentRoom.position.y
        DropAction().execute(currentRoom, board)
        projectedRoom = Room(currentRoom.roomElements.map { it -> it.copy() }.toMutableList(), currentRoom.position.cpy())
        currentRoom.position.y = positionY
        onProjectedRoomChanged.forEach { it.invoke() }
    }
}