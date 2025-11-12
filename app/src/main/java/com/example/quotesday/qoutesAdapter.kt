package com.example.quotesday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesday.databinding.QoutestemplateBinding


class QoutesAdapter(
    private var textqoutes: List<String>,
    private var textquoteswriter: List<String>
) : RecyclerView.Adapter<QoutesAdapter.QoutesViewHolder>() {

    private var onItemClick: ((String, String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QoutesViewHolder {
        val binding = QoutestemplateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QoutesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QoutesViewHolder, position: Int) {
        val quote = textqoutes[position]
        val writer = textquoteswriter[position]
        holder.bind(quote, writer)

        holder.binding.cardqoutes.setOnClickListener {
            onItemClick?.invoke(quote, writer)
        }
    }

    override fun getItemCount(): Int = textqoutes.size

    fun updateData(newQuotes: List<String>, newWriters: List<String>) {
        textqoutes = newQuotes
        textquoteswriter = newWriters
        notifyDataSetChanged()
    }

    class QoutesViewHolder(val binding: QoutestemplateBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: String, writer: String) {
            binding.textqoutes.text = quote
            binding.textwriter.text = writer
        }
    }
}
