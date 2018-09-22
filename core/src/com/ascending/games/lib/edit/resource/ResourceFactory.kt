package com.ascending.games.lib.edit.resource

class ResourceFactory {
    fun createResource(uri : String) : IResource {
        if (uri.endsWith(JSONResource.FILE_TYPE)) {
            return JSONResource(uri)
        } else {
            return DefaultResource(uri)
        }
    }
}