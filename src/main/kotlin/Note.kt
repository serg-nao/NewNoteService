package ru.netology

class Note (
    id: Int,
    private val title: String,
    private val text: String,
    val comments: MutableList<Comment> = mutableListOf()
): Node(id) {
    override fun toString(): String {
        return "$id with $title и $text and comments: $comments"
    }
}

