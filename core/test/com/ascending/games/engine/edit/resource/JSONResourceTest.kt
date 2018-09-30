package com.ascending.games.engine.edit.resource

import com.ascending.games.engine.edit.resource.JSONResource
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test


class JSONResourceTest {

    companion object {
        data class MockObject(var x : Int = 0)
    }

    val jsonResource = JSONResource("test.json")

    @Test
    fun serialize() {
        jsonResource.contents += MockObject(1)
        jsonResource.contents += MockObject(2)
        assertEquals("[{class:com.ascending.games.engine.edit.resource.JSONResourceTest\$Companion\$MockObject,x:1},{class:com.ascending.games.engine.edit.resource.JSONResourceTest\$Companion\$MockObject,x:2}]", jsonResource.serialize())
    }

    @Test
    fun deserialize() {
        val serialized = "[{class:com.ascending.games.engine.edit.resource.JSONResourceTest\$Companion\$MockObject,x:1},{class:com.ascending.games.engine.edit.resource.JSONResourceTest\$Companion\$MockObject,x:2}]"
        jsonResource.deserialize(serialized)
        assertThat(jsonResource.contents, hasItem(MockObject(1)))
        assertThat(jsonResource.contents, hasItem(MockObject(2)))
    }
}