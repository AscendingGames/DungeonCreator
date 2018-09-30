package com.ascending.games.engine.edit.resource

import com.badlogic.gdx.utils.Json


class JSONResource(override var uri : String) : com.ascending.games.engine.edit.resource.IResource {

    companion object {
        const val FILE_TYPE = ".json"
    }

    override val contents = mutableListOf<Any>()
    override var isLoaded  = false

    fun createJson() : Json {
        val json = Json()
        return json
    }

    override fun serialize(): String {
        val json = createJson()
        return json.toJson(contents)
    }

    override fun deserialize(serialized: String) {
        val json =  createJson()
        @Suppress("UNCHECKED_CAST")
        contents.addAll(json.fromJson(List::class.java, serialized) as List<Any>)
    }
}