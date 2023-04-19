package space.beka.mvvmpattern

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.beka.mvvmpattern.databinding.NoteItemBinding

class NoteAdapter(
    context: Context, val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) : RecyclerView.Adapter<NoteAdapter.Vh>() {
    private val list = ArrayList<Note>()
    inner class Vh(var itemRvBinding: NoteItemBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(note: Note, position: Int) {
            itemRvBinding.idTVNote.text = note.noteTitle
            itemRvBinding.idTVDate.setText("Last Updated : " + list.get(position).timeStamp)
            itemRvBinding.idIVDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(list.get(position))
        }
            itemRvBinding.root.setOnClickListener {
            noteClickInterface.onNoteClick( itemRvBinding.root ,list.get(position))
        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Note>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(view: View,note: Note)
}
