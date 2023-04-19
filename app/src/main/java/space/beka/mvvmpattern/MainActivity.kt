package space.beka.mvvmpattern

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import space.beka.mvvmpattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModal: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val noteRVAdapter = NoteAdapter(this, this, this)
        binding.notesRV.adapter = noteRVAdapter
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModal.allNotes.observe(this, Observer {
            it?.let {
                noteRVAdapter.updateList(it as ArrayList<Note>)
            }
        })
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(view: View, note: Note) {
        val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}