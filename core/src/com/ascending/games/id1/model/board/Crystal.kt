package com.ascending.games.id1.model.board

class Crystal(val type : Crystal.Type) : AClearable() {
    enum class Type {
        HEALING, ATTACK
    }
}