package com.ascending.games.engine.edit.resource

class ResourceFactory {
    fun createResource(uri : String) : com.ascending.games.engine.edit.resource.IResource {
        if (uri.endsWith(JSONResource.FILE_TYPE)) {
            return JSONResource(uri)
        } else {
            return com.ascending.games.engine.edit.resource.DefaultResource(uri)
        }
    }
}