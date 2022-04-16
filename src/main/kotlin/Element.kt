package ru.netology

interface Element<T: Node> {
    val elements: MutableList<T>
    var nextId: Int

    fun add(elem: T): Int {
        elem.id = nextId++
        elements.add(elem)
        return elements.last().id
    }

    fun update(elem: T): Boolean {
        for ((index, node) in elements.withIndex()) {
            if (node.id == elem.id) {
                elements[index] = elem
                return true
            }
        }
        return false
    }

    fun getById(id: Int): T? {
        return elements.find {it.id == id}
    }

    fun delete(id: Int): Boolean {
        for ((index, node) in elements.withIndex()) {
            if (node.id == id) {
                elements.removeAt(index)
                return true
            }
        }
        return false
    }
}