package com.ascending.games.lib.model.data

class ObservableMap<E, F>(private val mutableMap: MutableMap<E, F>) : IObservableMap<E, F> {
    override val onPut = HashSet<(E, F) -> Unit>()
    override val onRemove = HashSet<(E, F) -> Unit>()
    override val onChange = HashSet<(E, F) -> Unit>()
    override val size: Int
        get() = mutableMap.size
    override val entries: MutableSet<MutableMap.MutableEntry<E, F>>
        get() = mutableMap.entries
    override val keys: MutableSet<E>
        get() = mutableMap.keys
    override val values: MutableCollection<F>
        get() = mutableMap.values

    override fun containsKey(key: E): Boolean {
        return mutableMap.containsKey(key)
    }

    override fun containsValue(value: F): Boolean {
        return mutableMap.containsValue(value)
    }

    override fun get(key: E): F? {
        return mutableMap.get(key)
    }

    override fun isEmpty(): Boolean {
        return mutableMap.isEmpty()
    }

    override fun clear() {
        mutableMap.forEach { key, value ->
            onRemove.forEach { it.invoke(key, value) }
            onChange.forEach { it.invoke(key, value) }
        }
        mutableMap.clear()
    }

    override fun put(key: E, value: F): F? {
        onPut.forEach { it.invoke(key, value) }
        onChange.forEach { it.invoke(key, value) }
        return mutableMap.put(key, value)
    }

    override fun putAll(from: Map<out E, F>) {
        from.forEach { key, value -> put(key, value) }
    }

    override fun remove(key: E): F? {
        val value = mutableMap.remove(key)
        value ?: return null

        onRemove.forEach { it.invoke(key, value) }
        onChange.forEach { it.invoke(key, value) }

        return value
    }
}