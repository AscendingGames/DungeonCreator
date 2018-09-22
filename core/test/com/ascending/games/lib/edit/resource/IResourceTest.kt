package com.ascending.games.lib.edit.resource

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class IResourceTest {

    val mockResource = object : IResource {
        override var uri = "testUri"
        override val contents = mutableListOf<Any>()
        override var isLoaded = false

        override fun serialize() = "TEST"
        override fun deserialize(serialized: String) {
            contents.add("TEST")
        }
    }

    @Before
    fun setup() {
        Gdx.files  = LwjglFiles()
    }

    @After
    fun cleanUp() {
        if (mockResource.getFile().exists()) {
            mockResource.getFile().delete()
        }
    }

    @Test
    fun getFile() {
        assertNotNull(mockResource.getFile())
    }

    @Test
    fun save() {
        mockResource.contents.add("TEST")
        mockResource.save()
        assertTrue(mockResource.isLoaded)
        assertTrue(mockResource.getFile().exists())
        assertEquals("TEST", mockResource.getFile().readString())
    }

    @Test
    fun unload() {
        mockResource.save()
        mockResource.unload()
        assertFalse(mockResource.isLoaded)
        assertTrue(mockResource.contents.isEmpty())
    }

    @Test
    fun load() {
        mockResource.save()
        mockResource.unload()
        mockResource.load()
        assertTrue(mockResource.isLoaded)
        assertTrue(mockResource.contents.contains("TEST"))
    }

    @Test
    fun reload() {
        mockResource.save()
        mockResource.load()
        mockResource.contents.add("TEST2")
        mockResource.reload()
        assertTrue(mockResource.contents.contains("TEST"))
        assertFalse(mockResource.contents.contains("TEST2"))
    }
}