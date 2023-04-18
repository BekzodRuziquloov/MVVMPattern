package space.beka.mvvmpattern

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note :Note)
    @Delete
   fun delete(note: Note)
    @Update
    fun update(note: Note)
}