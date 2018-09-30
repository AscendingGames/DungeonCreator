package com.ascending.games.engine.edit.resource

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DefaultResourceTest {

    val defaultResource = com.ascending.games.engine.edit.resource.DefaultResource("testUri")

    @Before
    fun setup() {
        Gdx.files  = LwjglFiles()
        defaultResource.contents.add("TEST")
    }

    @After
    fun cleanUp() {
        if (defaultResource.getFile().exists()) {
            defaultResource.getFile().delete()
        }
    }

    @Test
    fun getFile() {
        assertNotNull(defaultResource.getFile())
    }

    @Test
    fun save() {
        defaultResource.save()
        assertTrue(defaultResource.isLoaded)
        assertTrue(defaultResource.getFile().exists())
        assertEquals("TEST", defaultResource.getFile().readString())
    }

    @Test
    fun unload() {
        defaultResource.save()
        defaultResource.unload()
        assertFalse(defaultResource.isLoaded)
        assertTrue(defaultResource.contents.isEmpty())
    }

    @Test
    fun load() {
        defaultResource.save()
        defaultResource.unload()
        defaultResource.load()
        assertTrue(defaultResource.isLoaded)
        assertTrue(defaultResource.contents.contains("TEST"))
    }

    @Test
    fun reload() {
        defaultResource.save()
        defaultResource.load()
        defaultResource.contents.add("TEST2")
        defaultResource.reload()
        assertTrue(defaultResource.contents.contains("TEST"))
        assertFalse(defaultResource.contents.contains("TEST2"))
    }
}