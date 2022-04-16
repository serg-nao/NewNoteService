package ru.netology

class NoteService : Element<Note> {
    private val notes = mutableListOf<Note>()
    override var nextId = 1
    override val elements
    get() = notes
}