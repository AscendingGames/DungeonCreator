package com.ascending.games.lib.model.game

interface IStatModifier {
    var appliedTo : IStats?
    fun apply(stats : IStats)
    fun unapply()
}