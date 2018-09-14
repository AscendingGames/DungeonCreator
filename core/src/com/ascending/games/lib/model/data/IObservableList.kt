package com.ascending.games.lib.model.data

interface IObservableList<E> : MutableList<E> {
    val onAdd : MutableCollection<(Int, E) -> Unit>
    val onRemove : MutableCollection<(E) -> Unit>

    override fun add(element: E): Boolean {
        val index = size - 1
        add(index, element)
        return true
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

    override fun remove(element: E): Boolean {
        val indexOf = indexOf(element)
        if (indexOf == - 1)  return false
        removeAt(indexOf)
        return true
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        return elements.any { remove(it) }
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        var changed = false
        for (i in size - 1 downTo 0) {
            val element = this[i]
            if (!elements.contains(element)) {
                removeAt(i)
                changed = true
            }
        }
        return changed
    }

    override fun set(index: Int, element: E): E {
        val oldElement = this[index]
        onRemove.forEach { it.invoke(oldElement) }
        this[index] = element
        onAdd.forEach { it.invoke(index, element) }
        return oldElement
    }
}