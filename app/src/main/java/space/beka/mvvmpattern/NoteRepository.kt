package space.beka.mvvmpattern

import androidx.lifecycle.LiveData


class NoteRepository(private val notesDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()
    fun insert(note: Note) {
        notesDao.insert(note)
    }

    fun delete(note: Note) {
        notesDao.delete(note)
    }

    fun update(note: Note) {
        notesDao.update(note)
    }
}