package com.example.noteapp.view.fragments.onnoteapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.model.data.models.NoteModel
import com.example.noteapp.databinding.FragmentDetailBinding
import com.example.noteapp.presenter.DetailContract
import com.example.noteapp.presenter.DetailsPresenter
import java.time.LocalDateTime

 class DetailFragment : Fragment(), DetailContract.View {
    private val presenter = DetailsPresenter(this@DetailFragment)
    private lateinit var binding: FragmentDetailBinding
    private lateinit var dateString : String
    private lateinit var timeString : String
    private lateinit var noteModel : NoteModel
    private var noteId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun getStringMonth(number : Int) : Int {
        return when (number) {
            1 -> R.string.month_january
            2 -> R.string.month_february
            3 -> R.string.month_march
            4 -> R.string.month_april
            5 -> R.string.month_may
            6 -> R.string.month_june
            7 -> R.string.month_july
            8 -> R.string.month_august
            9 -> R.string.month_september
            10 -> R.string.month_october
            11 -> R.string.month_november
            12 -> R.string.month_december
            else -> 0
        }
    }

    @SuppressLint("DefaultLocale")
    private fun initialize() {
        arguments?.let { args ->
            noteId = args.getInt("noteId")
        }
        val date = LocalDateTime.now()
        dateString = "${date.dayOfMonth} ${getString(getStringMonth(date.monthValue))}"
        presenter.loadNote(noteId)
        timeString = "${date.hour}:${String.format("%02d", date.minute)}"
        binding.date.text = dateString
        binding.time.text = timeString
        binding.edTxtTitle.setText(noteModel.title)
        binding.edTxtText.setText(noteModel.description)
    }

    private fun setupListeners() = with(binding) {
        edTxtTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                 isCorrecNote()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isCorrecNote()
            }

            override fun afterTextChanged(s: Editable?) {
                isCorrecNote()
            }

        })
        edTxtText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isCorrecNote()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isCorrecNote()
            }

            override fun afterTextChanged(s: Editable?) {
                isCorrecNote()
            }
        })

        binding.strelka.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.readyTextView.setOnClickListener {
            val title = binding.edTxtTitle.text.toString()
            val text = binding.edTxtText.text.toString()
            val date = dateString + timeString
            val note = NoteModel(title, text, date)

            Log.d("TAG", "setupListeners: ${note} ${noteId}")
            if (noteId == -1) {
                presenter.saveNote(note)
            } else {
                note.id = noteId
                presenter.updateNote(note)
            }
            findNavController().navigateUp()
        }
    }
    private fun isCorrecNote(){
        val title = binding.edTxtTitle.text
        val text = binding.edTxtText.text
        if (title.isNotEmpty() && text.isNotEmpty()) {
            binding.readyTextView.visibility = View.VISIBLE
        } else {
            binding.readyTextView.visibility = View.GONE
        }
    }

    override fun showNote(note: NoteModel) {
        noteModel = note
        Log.d("TAG", "showNote: ${noteModel} ${note.id}")
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
    }
}