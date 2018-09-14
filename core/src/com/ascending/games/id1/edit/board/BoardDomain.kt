package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.content.HeroActionProvider
import com.ascending.games.id1.edit.board.action.content.IRoomContentAction
import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.edit.board.action.room.IBoardAction
import com.ascending.games.id1.model.board.*
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class BoardDomain(val board: Board, private val roomFactory : IRoomFactory) {

    companion object {
        const val COUNT_WAITING_ROOMS = 3
    }

    var waitingRooms = List(COUNT_WAITING_ROOMS) { roomFactory.createRoom() }
    var currentRoom by Delegates.notNull<Room>()
    var time = 0f
    val heroActionProvider = HeroActionProvider(board.hero)

    val mapRoomContentToActionList = mutableMapOf<ARoomContent, List<IRoomContentAction>>()

    init {
        nextRoom()
    }

    fun update(time : Float) {
        var positionChanged = false
        for (room in board.rooms) {
            if (!board.hasRoomFallen(room)) {
                room.position.y -= time
                positionChanged = true
            } else {
                room.position.y = room.position.y.roundToInt().toFloat()
                openWallsNeighbouringDoors(room)
            }
        }

        if (!positionChanged) {
            if (!board.hero.spawned) {
                board.hero.spawn(board)
            }

            nextRoom()
        }

        for (row in 0 until board.height) {
            clearRowIfFull(row)
        }

        var heroActionList = mapRoomContentToActionList.get(board.hero) ?: emptyList()
        if (heroActionList.isEmpty()) {
            heroActionList = heroActionProvider.getNextActions(this)
            mapRoomContentToActionList.put(board.hero, heroActionList)
        }

        if (!heroActionList.isEmpty()) {
            val action = heroActionList[0]
            if (action.canExecute(this)) {
                if (action.execute(this, time)) {
                    mapRoomContentToActionList.put(board.hero, heroActionList.minus(action))
                }
            } else {
                mapRoomContentToActionList.put(board.hero, emptyList())
            }
        }
    }

    fun execute(action : IBoardAction) {
        action.execute(currentRoom,this)
    }

    fun getProjectedRoom() : Room {
        val projectedRoom = Room(currentRoom.roomElements.map { it -> it.copy() })
        DropAction().execute(projectedRoom, this)
        return projectedRoom
    }

    private fun getClearedElements(row : Int) : List<RoomElement> {
        var clearedElements = emptyList<RoomElement>()

        for (x in 0 until board.width) {
            val roomElement = board.getRoomElementAt(Coord2(x, row))
            roomElement ?: return emptyList()

            if (board.hero.roomElement == roomElement) return emptyList()
            
            if (!board.hasRoomFallen(roomElement.room)) {
                return emptyList()
            }

            clearedElements += roomElement
        }

        return clearedElements
    }

    fun clearRowIfFull(row : Int) : Boolean {
        val clearedElements = getClearedElements(row)
        for (roomElement in clearedElements) {
            val room = roomElement.room
            room.roomElements -= roomElement
            if (room.roomElements.isEmpty()) {
                board.rooms.remove(room)
            }
        }

        return !clearedElements.isEmpty()
    }

    fun openWallsNeighbouringDoors(room : Room) {
        for (roomElement in room.roomElements) {
            val wallsToOpen = board.getWallsToOpen(roomElement)
            wallsToOpen.forEach { it.roomElement.walls -= it }
        }
    }

    fun nextRoom() {
        currentRoom = waitingRooms[0]
        currentRoom.position = Vector2(((board.width - 1) / 2).toFloat(), board.height.toFloat() - 1)
        waitingRooms = waitingRooms.drop(1) + roomFactory.createRoom()
        board.rooms.add(currentRoom)
    }
}