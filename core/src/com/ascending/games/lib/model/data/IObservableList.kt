package com.ascending.games.lib.model.data

interface IObservableList<E> : MutableList<E> {
    val onAdd : MutableCollection<(Int, E) -> Unit>
    val onRemove : MutableCollection<(E) -> Unit>
}