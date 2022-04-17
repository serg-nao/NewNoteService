package ru.netology

class NoteService : Element<Note> {
    val notes = mutableListOf<Note>()
    override var nextId = 1
    override val elements
    get() = notes

    fun createComment(noteId: Int, comment: Comment): Int? {
        var lastId = getById(noteId)?.comments?.size
        if (lastId != 0) {
            comment.id = lastId!! + 1
        } else {
            comment.id = 1
        }
        getById(noteId)?.comments?.add(comment)
        return getById(noteId)?.comments?.last()?.id
    }

    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        val thisComment = getById(noteId)?.comments?.elementAt(commentId - 1)
        if (thisComment != null && !thisComment.deleted) {
            getById(noteId)!!.comments[commentId - 1].deleted = true
            return true
        }
        return false
    }

    fun editComment(noteId: Int, commentId: Int, message: String): Boolean {
        val thisComment = getById(noteId)?.comments?.elementAt(commentId - 1)
        if (!getById(noteId)?.comments?.elementAt(commentId - 1)?.deleted!!) {
                getById(noteId)!!.comments[commentId - 1].message = message
                return true
            }
        return false
    }

    fun get(noteIds: MutableList<Int>, count: Int): MutableList<Note> {
        var listOfNotes = mutableListOf<Note>()
        var thisCount: Int = count
        for (i in notes.indices) {
            for (j in noteIds.indices) {
                if ((noteIds[j] == notes[i].id) && (thisCount > 0)) {
                    listOfNotes += notes[i]
                    thisCount --
                }
            }
        }
        return listOfNotes
    }

    fun getComments(noteId: Int, count: Int): MutableList<Comment> {
        var listOfComments = mutableListOf<Comment>()
        var thisCount: Int = count
        if (getById(noteId) != null) {
            for (index in getById(noteId)!!.comments.indices) {
                if (!getById(noteId)!!.comments[index].deleted && (thisCount > 0)) {
                    listOfComments += getById(noteId)!!.comments[index]
                    thisCount--
                }
            }
            return listOfComments
        }
        return listOfComments
    }

    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        val thisComment = getById(noteId)?.comments?.elementAt(commentId - 1)
        if (thisComment != null && thisComment.deleted) {
            getById(noteId)!!.comments[commentId - 1].deleted = false
            return true
        }
        return false
    }
}