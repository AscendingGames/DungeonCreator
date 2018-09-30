package com.ascending.games.lib.edit.resource

import org.junit.Assert.assertTrue
import org.junit.Test

class ResourceFactoryTest {

    val resourceFactory = ResourceFactory()

    @Test
    fun createResource() {
        assertTrue(resourceFactory.createResource("test.json") is JSONResource)
        assertTrue(resourceFactory.createResource("test.txt") is DefaultResource)
    }
}