package com.ascending.games.lib.model.data

class ObservableList<E>(private val mutableList: MutableList<E>) : MutableList<E> {
    val onAdd = HashSet<(Int, E) -> Unit>()
    val onRemove = HashSet<(E) -> Unit>()

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

    override fun add(element: E): Boolean {
        mutableList.add(element)
        val index = mutableList.size - 1
        onAdd.forEach { it.invoke(index, element) }
        return true
    }

    override fun add(index: Int, element: E) {
        mutableList.add(element)
        onAdd.forEach { it.invoke(index, element) }
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        var currentIndex = index
        for (element in elements) {
            add(currentIndex++, element)
        }
        return true
    }

    override fun addAll(elements: Collection<E>): Boolean {
        for (element in elements) {
            add(element)
        }
        return true
    }

    override fun clear() {
        for (element in mutableList) {
            onRemove.forEach { it.invoke(element) }
        }
        onRemove.clear()
    }

    override fun listIterator(): MutableListIterator<E> {
        return mutableList.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return mutableList.listIterator(index)
    }

    override fun remove(element: E): Boolean {
        onRemove.forEach { it.invoke(element) }
        return mutableList.remove(element)
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        return elements.any { remove(it) }
    }

    override fun removeAt(index: Int): E {
        val element = mutableList[index]
        onRemove.forEach { it.invoke(element) }
        mutableList.removeAt(index)
        return element
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        var changed = false
        for (i in mutableList.size - 1 downTo 0) {
            val element = mutableList[i]
            if (!elements.contains(element)) {
                removeAt(i)
                changed = true
            }
        }
        return changed
    }

    override fun set(index: Int, element: E): E {
        val oldElement = mutableList[index]
        onRemove.forEach { it.invoke(oldElement) }
        mutableList[index] = element
        onAdd.forEach { it.invoke(index, element) }
        return oldElement
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        return ObservableList(mutableList.subList(fromIndex, toIndex))
    }
}