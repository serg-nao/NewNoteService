package ru.netology

fun main() {
    val serviceNote = NoteService()
    val firstNote = Note(1, "Title", "Text", 1, 1, mutableListOf())
    val firstComment = Comment(1, 1, "message", null, null, false)
    val secondComment = Comment(1, 1, "message", null, null, false)
    val secondNote = Note(1, "Title 2", "Text 2", 1, 1, mutableListOf())
    serviceNote.add(firstNote)
    serviceNote.add(secondNote)
    println(serviceNote.createComment(1, firstComment))
    println(serviceNote.createComment(1, secondComment))
    println(serviceNote.elements[0].comments[1].message)
    println(serviceNote.editComment(1, 2, "test text"))
    println(serviceNote.editComment(1, 3, "test text"))
    println(serviceNote.elements[0].comments[1].message)
    println(serviceNote.deleteComment(1, 2))
}