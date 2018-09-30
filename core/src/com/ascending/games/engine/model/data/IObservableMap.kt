package com.ascending.games.engine.model.data

interface IObservableMap<E, F> : MutableMap<E, F> {
    val onPut : MutableCollection<(E, F) -> Unit>
    val onRemove : MutableCollection<(E, F) -> Unit>
    val onChange : MutableCollection<(E, F) -> Unit>

    override fun putAll(from: Map<out E, F>) {
        from.forEach { key, value -> put(key, value) }
    }
}