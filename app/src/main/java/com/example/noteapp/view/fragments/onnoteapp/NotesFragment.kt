package com.example.noteapp.view.fragments.onnoteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.FragmentNotesBinding
import com.example.noteapp.model.data.models.NoteModel
import com.example.noteapp.presenter.list.NotesContract
import com.example.noteapp.presenter.list.NotesPresenter
import com.example.noteapp.view.adapters.NoteAdapter
import com.example.noteapp.view.inter.OnClickItem

class NotesFragment : Fragment(), OnClickItem, NotesContract.View {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var noteAdapter: NoteAdapter
    private var lm = true
    private val presenter = NotesPresenter(this@NotesFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        setupListeners()
        presenter.loadNotes()
    }

    private fun initialize() {
        noteAdapter = NoteAdapter(this)
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNote.adapter = noteAdapter
    }

    private fun setupListeners() {
        binding.btnPluse.setOnClickListener {
            findNavController().navigate(
                NotesFragmentDirections.actionNotesFragmentToDetailFragment(
                    -1
                )
            )
        }
        binding.sp.setOnClickListener {
            if (lm) {
                binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                noteAdapter.setLayoutMode(false)
                lm = false
            } else {
                binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
                noteAdapter.setLayoutMode(true)
                lm = true
            }
        }
    }


    override fun onLongClick(noteModel: NoteModel) = with(binding) {
        alertDialogScreen.visibility = View.VISIBLE

        alertDialogScreen.setOnClickListener {

        }
        positiveButton.setOnClickListener {
           presenter.deleteNote(noteModel)
        //         App.appDatabase?.noteDao()?.deleteNote(noteModel)
               alertDialogScreen.visibility = View.GONE
        }
        negativeButton.setOnClickListener {
            alertDialogScreen.visibility = View.GONE
        }

    }


    override fun onClick(noteModel: NoteModel) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToDetailFragment(
                noteModel.id
            )
        )
    }

    override fun showNotes(notes: List<NoteModel>) {
        noteAdapter.submitList(notes)
    }

    override fun showError(massage: String) {
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
    }
}