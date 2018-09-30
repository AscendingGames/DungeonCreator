package com.ascending.games.engine.model.data

class ObservableList<E>(private val mutableList: MutableList<E>) : IObservableList<E> {
    override val onAdd = HashSet<(Int, E) -> Unit>()
    override val onRemove = HashSet<(E) -> Unit>()
    override val size: Int get() = mutableList.size

    override fun contains(element: E): Boolean {
        return mutableList.contains(element)
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return mutableList.containsAll(elements)
    }

    override fun get(index: Int): E {
        return mutableList[index]
    }

    override fun indexOf(element: E): Int {
        return mutableList.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return mutableList.isEmpty()
    }

    override fun iterator(): MutableIterator<E> {
        return mutableList.iterator()
    }

    override fun lastIndexOf(element: E): Int {
        return mutableList.lastIndexOf(element)
    }

    override fun add(index: Int, element: E) {
        mutableList.add(element)
        onAdd.forEach { it.invoke(index, element) }
    }
    override fun clear() {
        val oldList = mutableList.toList()
        mutableList.clear()
        oldList.forEach { element -> onRemove.forEach { it.invoke(element) } }
    }

    override fun listIterator(): MutableListIterator<E> {
        return mutableList.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return mutableList.listIterator(index)
    }

    override fun removeAt(index: Int): E {
        val element = this[index]
        onRemove.forEach { it.invoke(element) }
        mutableList.removeAt(index)
        return element
    }

    override fun set(index: Int, element: E): E {
        val oldElement = this[index]
        onRemove.forEach { it.invoke(oldElement) }
        mutableList[index] = element
        onAdd.forEach { it.invoke(index, element) }
        return oldElement
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        return ObservableList(mutableList.subList(fromIndex, toIndex))
    }
}