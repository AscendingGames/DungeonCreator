package com.ascending.games.id1.model.mechanics

enum class Ritual(val goldCosts : Int, val hpCosts : Int, val unlockDepth : Int) {
    RitualOfBlessings(100, 1, 2),
    RitualOfVisions(500, 10, 5),
    RitualOfEnlightenment(1000, 10, 10)
}