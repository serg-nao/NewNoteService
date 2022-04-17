package ru.netology

class Comment (
    id: Int,
    val noteId: Int,
    var message: String,
    val ownerId: Int? = null,
    val replyTo: Int? = null,
    var deleted: Boolean = false
): Node(id) {
    override fun toString(): String {
        return "$message"
    }
}

