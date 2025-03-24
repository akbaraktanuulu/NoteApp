package com.example.noteapp.ui.fragments.onnoteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteAppBinding
import com.example.noteapp.models.NoteAppModel
import com.example.noteapp.ui.adapters.NoteAppPageAdapter

class NoteAppFragment : Fragment() {
    private lateinit var binding: FragmentNoteAppBinding

    private var noteAppPageAdapter: NoteAppPageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListners()
    }

    private fun initialize() {
        noteAppPageAdapter = NoteAppPageAdapter(generateDataNoteApp(), this@NoteAppFragment)
        binding.viewpager.adapter = noteAppPageAdapter
    }

    fun generateDataNoteApp() = listOf<NoteAppModel>(
        NoteAppModel(
             R.raw.convenience_animation,
            "Удобство",
            "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
        ),
        NoteAppModel(
             R.raw.organization_animation,
            "Организация",
            "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
        ),
        NoteAppModel(
             R.raw.synchronization_animation,
            "Синхронизация",
            "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
        )
    )

    private fun setupListners() {
        binding.viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeActiveOnboardShower(position)
                binding.startBtn.isVisible = position == 2
                binding.skip.visibility = if (position != 2) View.VISIBLE else View.INVISIBLE
            }
        })
        binding.startBtn.setOnClickListener{
            findNavController().navigate(R.id.action_noteAppFragment_to_notesFragment)
        }
    }
    private fun changeActiveOnboardShower(position : Int) {
        val onboardShowers = binding.onBordShort;
        for (i in 0 until onboardShowers.childCount) {
            val onboardShower = onboardShowers.getChildAt(i)
            if (i == position) {
                onboardShower.setBackgroundResource(R.drawable.ic_active_view_element)
            } else {
                onboardShower.setBackgroundResource(R.drawable.ic_view_element)
            }
        }
    }
}