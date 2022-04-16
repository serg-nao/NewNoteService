package ru.netology

fun main() {
    val serviceNote = NoteService()
    println(serviceNote.elements)
    val iNote = serviceNote.add(Note(1))
    println(serviceNote.elements)
    println("Последний id = $iNote")
    println(serviceNote.update(Note(1)))
    println(serviceNote.getById(1))
    println(serviceNote.getById(2))


    val serviceComment = CommentService()
    println(serviceComment.elements)
    val iComment = serviceComment.add(Comment(1))
    println(serviceComment.elements)
    println("Последний id = $iComment")
    println(serviceComment.update(Comment(1)))
    println(serviceComment.getById(1))
}