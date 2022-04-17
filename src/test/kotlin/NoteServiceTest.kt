import org.junit.Test

import org.junit.jupiter.api.Assertions.*
import ru.netology.Comment
import ru.netology.Note
import ru.netology.NoteService

internal class NoteServiceTest {
    private val noteService = NoteService()
    val idFirst = noteService.add(
    Note(1, "First title", "First text", 1, 1, mutableListOf())
    )
    val idSecond = noteService.add(
        Note(2, "Second title", "Second text", 1, 1, mutableListOf())
    )
    private val testComment = Comment(1, 1, "First message", null, null, false)
    private val testCommentDeleted = Comment(1, 1, "First message", null, null, true)


    @Test
    fun createComment() {
        //arrange
        val testNoteId = 1

        //act
        var lastId = noteService.getById(testNoteId)?.comments?.size
        if (lastId != 0) {
            testComment.id = lastId!!
        } else {
            testComment.id = 1
        }
        noteService.getById(testNoteId)?.comments?.add(testComment)
        val result = noteService.getById(testNoteId)?.comments?.last()?.id

        //assert
        assertEquals(1, result)
    }

    @Test
    fun deleteComment_True() {
        //arrange
        val testNoteId = 1
        val testCommentId = 1
        val idComment = noteService.createComment(1, testComment)

        //act
        val thisComment = noteService.getById(testNoteId)?.comments?.elementAt(testCommentId - 1)
        if (thisComment != null && !thisComment.deleted) {
            noteService.getById(testNoteId)!!.comments[testCommentId - 1].deleted = true
        }
        val result: Boolean = noteService.getById(testNoteId)!!.comments[testCommentId - 1].deleted

        //assert
        assertTrue(result)
    }

    @Test
    fun deleteComment_False() {
        //arrange
        val testNoteId = 1
        val testCommentId = 1
        val idComment = noteService.createComment(1, testCommentDeleted)

        //act
        val thisComment = noteService.getById(testNoteId)?.comments?.elementAt(testCommentId - 1)
        if (thisComment != null && !thisComment.deleted) {
            noteService.getById(testNoteId)!!.comments[testCommentId - 1].deleted = true
        }
        val result: Boolean = !noteService.getById(testNoteId)!!.comments[testCommentId - 1].deleted

        //assert
        assertFalse(result)
    }

    @Test
    fun editComment_True() {
        //arrange
        val testNoteId = 1
        val testCommentId = 1
        val testMessage = "test"
        val idComment = noteService.createComment(1, testComment)

        //act
        val thisComment = noteService.getById(testNoteId)?.comments?.elementAt(testCommentId - 1)
        if (!noteService.getById(testNoteId)?.comments?.elementAt(testCommentId - 1)?.deleted!!) {
            noteService.getById(testNoteId)!!.comments[testCommentId - 1].message = testMessage
        }
        val result: Boolean = (noteService.getById(testNoteId)!!.comments[testCommentId - 1].message == testMessage)

        //assert
        assertTrue(result)
    }

    @Test
    fun editComment_False() {
        //arrange
        val testNoteId = 1
        val testCommentId = 1
        val testMessage = "test"
        val idComment = noteService.createComment(1, testComment)
        val deleted = noteService.deleteComment(1, 1)

        //act
        val thisComment = noteService.getById(testNoteId)?.comments?.elementAt(testCommentId - 1)
        if (!noteService.getById(testNoteId)?.comments?.elementAt(testCommentId - 1)?.deleted!!) {
            noteService.getById(testNoteId)!!.comments[testCommentId - 1].message = testMessage
        }
        val result: Boolean = (noteService.getById(testNoteId)!!.comments[testCommentId - 1].message == testMessage)

        //assert
        assertFalse(result)
    }

    @Test
    fun get() {
        //arrange
        val noteIds: MutableList<Int> = mutableListOf(1, 2)
        val count = 2

        //act
        var listOfNotes = mutableListOf<Note>()
        var thisCount: Int = count
        for (i in noteService.notes.indices) {
            for (j in noteIds.indices) {
                if ((noteIds[j] == noteService.notes[i].id) && (thisCount > 0)) {
                    listOfNotes += noteService.notes[i]
                    thisCount --
                }
            }
        }
        val result = listOfNotes.size

        //assert
        assertEquals(2, result)
    }

    @Test
    fun getComments() {
        //arrange
        val testNoteId = 1
        val count = 2
        val idComment = noteService.createComment(1, testComment)
        val oneMoreIdComment = noteService.createComment(1, Comment(1, 1, "my text", null, null, false))

        //act
        var listOfComments = mutableListOf<Comment>()
        var thisCount: Int = count
        if (noteService.getById(testNoteId) != null) {
            for (index in noteService.getById(testNoteId)!!.comments.indices) {
                if (!noteService.getById(testNoteId)!!.comments[index].deleted && (thisCount > 0)) {
                    listOfComments += noteService.getById(testNoteId)!!.comments[index]
                    thisCount--
                }
            }
        }
        val result = listOfComments.size

        //assert
        assertEquals(2, result)
    }

    @Test
    fun restoreComment_true() {
        //arrange
        val testNoteId = 1
        val commentId = 1
        val idComment = noteService.createComment(1, testComment)
        val deleteIndex = noteService.deleteComment(1, 1)

        //act
        val thisComment = noteService.getById(testNoteId)?.comments?.elementAt(commentId - 1)
        if (thisComment != null && thisComment.deleted) {
            noteService.getById(testNoteId)!!.comments[commentId - 1].deleted = false
        }
        val result: Boolean = (!noteService.getById(testNoteId)!!.comments[commentId - 1].deleted)

        //assert
        assertTrue(result)
    }

    @Test
    fun restoreComment_false() {
        //arrange
        val testNoteId = 1
        val commentId = 1
        val idComment = noteService.createComment(1, testComment)

        //act
        val thisComment = noteService.getById(testNoteId)?.comments?.elementAt(commentId - 1)
        if (thisComment != null && thisComment.deleted) {
            noteService.getById(testNoteId)!!.comments[commentId - 1].deleted = false
        }
        val result: Boolean = (noteService.getById(testNoteId)!!.comments[commentId - 1].deleted)

        //assert
        assertFalse(result)
    }
}