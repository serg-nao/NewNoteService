package ru.netology

class Note (
    id: Int,
    private val title: String,
    private val text: String,
    var privacy: Int = 1,
    var commentPrivacy: Int = 1,
    val comments: MutableList<Comment> = mutableListOf()
): Node(id) {
    override fun toString(): String {
        return "$id with $title и $text and comments: $comments"
    }
}

