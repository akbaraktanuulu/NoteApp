package com.example.noteapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp.models.NoteAppModel
import com.example.noteapp.ui.fragments.onnoteapp.NoteAppPageFragment

class NoteAppPageAdapter(
    private val list: List<NoteAppModel>,
    fragment: Fragment
) :
    FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment = NoteAppPageFragment().apply {
        val bundle = Bundle()

        bundle.putSerializable("NOTE_APP", list[position])
        arguments = bundle
    }

    override fun getItemCount(): Int = list.size
}