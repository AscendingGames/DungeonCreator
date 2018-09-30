package com.ascending.games.engine.model.game

interface IStatModifier {
    var appliedTo : IStats?
    fun apply(stats : IStats)
    fun unapply()
}