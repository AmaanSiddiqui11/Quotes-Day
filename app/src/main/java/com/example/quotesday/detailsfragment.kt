package com.example.quotesday

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.quotesday.databinding.FragmentDetailsfragmentBinding

class detailsfragment : Fragment() {


    private var _binding: FragmentDetailsfragmentBinding? = null
    private val binding get() = _binding!!


    private var quote: String? = null
    private var writer: String? = null


    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            quote = it.getString("quote")
            writer = it.getString("writer")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textqoutes1.text = quote ?: "No Quote Found"
        binding.textwriter1.text = "- ${writer ?: "Unknown"}"


        binding.redheart.setOnClickListener {
            isLiked = !isLiked

            if (isLiked) {
                binding.redheart.setImageResource(R.drawable.fillredish)
                binding.redheart.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_light)
                )
            } else {
                binding.redheart.setImageResource(R.drawable.outline_favorite_2422)
                binding.redheart.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.black)
                )
            }


            binding.redheart.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction {
                binding.redheart.animate().scaleX(1f).scaleY(1f).setDuration(100)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
