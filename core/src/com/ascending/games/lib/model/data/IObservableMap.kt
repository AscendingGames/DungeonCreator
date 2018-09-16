package com.ascending.games.lib.model.data

interface IObservableMap<E, F> : MutableMap<E, F> {
    val onPut : MutableCollection<(E, F) -> Unit>
    val onRemove : MutableCollection<(E, F) -> Unit>
    val onChange : MutableCollection<(E, F) -> Unit>
}