package com.ascending.games.engine.edit.resource

class DefaultResource(override var uri: String) : com.ascending.games.engine.edit.resource.IResource {
    override val contents = mutableListOf<Any>()
    override var isLoaded  = false

    override fun serialize(): String {
        return contents.joinToString("\n")
    }

    override fun deserialize(serialized: String) {
        contents.addAll(serialized.split("\n"))
    }
}