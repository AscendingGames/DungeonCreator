package com.ascending.games.id1.view.mechanics

import com.ascending.games.id1.model.mechanics.Ritual

class RitualLabelProvider {
    fun getValue(ritual : Ritual) : String {
        return when (ritual) {
            Ritual.RitualOfVisions -> "Ritual of Visions"
            Ritual.RitualOfEnlightenment -> "Ritual of Enlightenment"
            Ritual.RitualOfBlessings -> "Ritual of Blessings"
        }
    }
}