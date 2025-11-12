package com.example.quotesday

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesday.databinding.FragmentMainfragmentBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainfragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: QoutesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.leftIcon.setOnClickListener {
            Toast.makeText(requireContext(), "Menu", Toast.LENGTH_SHORT).show()
        }

        binding.rightIcon.setOnClickListener {
            Toast.makeText(requireContext(), "Filter", Toast.LENGTH_SHORT).show()
        }

        val baseQuotes = listOf(
            Triple("Believe in yourself.", "Arif Qazi", "Proverbs"),
            Triple("Dream big and dare to fail.", "Qazi Shoyab", "Books"),
            Triple("Every day is a second chance.", "Albert Einstein", "Life"),
            Triple("Push yourself because no one else will do it for you.", "Steve Jobs", "Maintility"),
            Triple("Stay positive, work hard, make it happen.", "Arif Qazi", "Emotions"),
            Triple("You are stronger than you think.", "Qazi Shoyab", "Famous"),
            Triple("Love is the beauty of the soul.", "Elon Musk", "Emotions"),
            Triple("Life is short, smile while you still have teeth.", "Dr. APJ Abdul Kalam", "Life"),
            Triple("Be kind whenever possible. It is always possible.", "Dalai Lama", "Proverbs"),
            Triple("Reading gives us someplace to go when we have to stay where we are.", "Mason Cooley", "Books")
        )

        val quotesData = List(1000) { index ->
            val base = baseQuotes[index % baseQuotes.size]
            Triple(
                "${base.first} (#${index + 1})",
                base.second,
                base.third
            )
        }

        adapter = QoutesAdapter(
            textqoutes = quotesData.map { it.first },
            textquoteswriter = quotesData.map { it.second }
        )

        binding.strecycleview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.strecycleview.adapter = adapter

        adapter.setOnItemClickListener { quote, writer ->
            val fragment = detailsfragment().apply {
                arguments = Bundle().apply {
                    putString("quote", quote)
                    putString("writer", writer)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .addToBackStack(null)
                .commit()
        }

        fun showFilteredQuotes(category: String) {
            val filteredQuotes = if (category == "All") {
                quotesData
            } else {
                quotesData.filter { it.third.equals(category, ignoreCase = true) }
            }

            adapter.updateData(
                filteredQuotes.map { it.first },
                filteredQuotes.map { it.second }
            )
        }

        val buttons = listOf(
            binding.btnAll,
            binding.btnProverbs,
            binding.btnBooks,
            binding.btnLife,
            binding.btnFamous,
            binding.btnEmotions,
            binding.btnMaintaility
        )

        val defaultBg = ContextCompat.getColor(requireContext(), R.color.white)
        val selectedBg = ContextCompat.getColor(requireContext(), R.color.black)
        val defaultText = ContextCompat.getColor(requireContext(), android.R.color.black)
        val selectedText = ContextCompat.getColor(requireContext(), android.R.color.white)



        buttons.forEach {
            it.backgroundTintList = ColorStateList.valueOf(defaultBg)
            it.setTextColor(defaultText)
        }
        binding.btnAll.backgroundTintList = ColorStateList.valueOf(selectedBg)
        binding.btnAll.setTextColor(selectedText)
        showFilteredQuotes("All")

        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach {
                    it.backgroundTintList = ColorStateList.valueOf(defaultBg)
                    it.setTextColor(defaultText)
                }

                button.backgroundTintList = ColorStateList.valueOf(selectedBg)
                button.setTextColor(selectedText)


                when (button) {
                    binding.btnAll -> showFilteredQuotes("All")
                    binding.btnProverbs -> showFilteredQuotes("Proverbs")
                    binding.btnBooks -> showFilteredQuotes("Books")
                    binding.btnLife -> showFilteredQuotes("Life")
                    binding.btnFamous -> showFilteredQuotes("Famous")
                    binding.btnEmotions -> showFilteredQuotes("Emotions")
                    binding.btnMaintaility -> showFilteredQuotes("Maintility")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
