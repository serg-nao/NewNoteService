package ru.netology

class CommentService : Element<Comment> {
    private val comments = mutableListOf<Comment>()
    override var nextId = 1
    override val elements
        get() = comments
}