package com.ascending.games.id1.view.world

class ShrineView(worldScreen: WorldScreen) : ALocationView(worldScreen, SHRINE_TEXT) {
    companion object {
        const val SHRINE_TEXT =
                "This is your shrine, nothing but a humongous stone\n"+
                "with random inscriptions scribbled on it.\n"+
                "Whoever did this had not expected any results.\n" +
                "A creation of despair -- a creation of wishes..."
    }

    private val buttonBack = createBackButton()

    init {
        locationTable.add(buttonBack)
    }
}