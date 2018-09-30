package com.ascending.games.engine.edit.resource

import com.ascending.games.engine.edit.resource.JSONResource
import com.ascending.games.engine.edit.resource.ResourceFactory
import org.junit.Assert.assertTrue
import org.junit.Test

class ResourceFactoryTest {

    val resourceFactory = ResourceFactory()

    @Test
    fun createResource() {
        assertTrue(resourceFactory.createResource("test.json") is JSONResource)
        assertTrue(resourceFactory.createResource("test.txt") is com.ascending.games.engine.edit.resource.DefaultResource)
    }
}