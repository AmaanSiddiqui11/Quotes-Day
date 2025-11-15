package com.example.quotesday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesday.databinding.QoutestemplateBinding

class QoutesAdapter(
    private var quotes: MutableList<QuotesData>   // <-- IMPORTANT
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

    // ============================
    //      FILTER UPDATE CODE
    // ============================
    fun updateData(newList: List<QuotesData>) {
        quotes.clear()
        quotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: QoutesViewHolder, position: Int) {
        val item = quotes[position]
        holder.bind(item)

        holder.binding.cardqoutes.setOnClickListener {
            onItemClick?.invoke(item.quotes, item.writer)
        }
    }

    override fun getItemCount(): Int = quotes.size

    class QoutesViewHolder(val binding: QoutestemplateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quotesData: QuotesData) {
            binding.textqoutes.text = quotesData.quotes
            binding.textwriter.text = quotesData.writer
        }
    }
}
