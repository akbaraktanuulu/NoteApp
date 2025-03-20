package com.example.noteapp.ui.fragments.onnoteapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.FragmentNoteAppPageBinding
import com.example.noteapp.models.NoteAppModel

class NoteAppPageFragment : Fragment() {
    private lateinit var binding: FragmentNoteAppPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteAppPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        val noteApp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("NOTE_APP", NoteAppModel::class.java)
        } else {
            arguments?.getSerializable("NOTE_APP") as? NoteAppModel
        }
        noteApp?.let { note ->
            binding.animation.setAnimation(note.animation)
            binding.titleTxt.text = note.title
            binding.descTxt.text = note.desc
        }
    }
}