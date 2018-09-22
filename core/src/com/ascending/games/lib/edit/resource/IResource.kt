package com.ascending.games.lib.edit.resource

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

interface IResource : ISaveable {
    var uri : String
    val contents : MutableList<Any>
    var isLoaded : Boolean

    fun serialize() : String
    fun deserialize(serialized : String)

    fun getFile() : FileHandle {
        return Gdx.files.local(uri)
    }

    override fun save() {
        val serialized = serialize()
        getFile().writeString(serialized, false)
    }

    override fun load() {
        if (!isLoaded) {
            val serialized = getFile().readString()
            deserialize(serialized)

            isLoaded = true
        }
    }
}