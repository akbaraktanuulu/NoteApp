package com.example.noteapp.ui.fragments.onnoteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.db.AppDatabace
import com.example.noteapp.databinding.FragmentDetailBinding
import com.example.noteapp.databinding.FragmentNotesBinding
import com.example.noteapp.ui.adapters.NoteAdapter

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var noteAdapter : NoteAdapter

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
        getDatabase()
    }

    private fun initialize() {
        noteAdapter = NoteAdapter()
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNote.adapter = noteAdapter
    }

    private fun setupListeners() {
        binding.btnPluse.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_detailFragment)
        }
    }


    private fun getDatabase() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {notes ->
            noteAdapter.submitList(notes)
        }
    }


}