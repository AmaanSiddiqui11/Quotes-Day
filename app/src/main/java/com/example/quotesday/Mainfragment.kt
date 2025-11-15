package com.example.quotesday

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quotesday.databinding.FragmentMainfragmentBinding
import kotlin.collections.filter


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


//        val quotesList = mutableListOf(
//
//            QuotesData("Believe in yourself and you will be unstoppable.", "Arif Qazi", "Proverbs"),
//            QuotesData("Life is not lived on the sidelines.", "Unknown", "Life"),
//
//            QuotesData("Success is not final; failure is not fatal.", "Winston Churchill", "Famous"),
//            QuotesData("Feelings are visitors; let them come and go.", "Unknown", "Emotions"),
//
//            QuotesData("A reader lives a thousand lives.", "George R.R. Martin", "Books"),
//            QuotesData("Actions speak louder than words.", "Unknown", "Proverbs"),
//            QuotesData("The purpose of life is to grow.", "Unknown", "Life"),
//            QuotesData("Dream big and dare to fail.", "Norman Vaughan", "Famous"),
//            QuotesData("Emotions are the language of the soul.", "Unknown", "Emotions"),
//
//            QuotesData("Books are a uniquely portable magic.", "Stephen King", "Books"),
//
//            QuotesData("Life teaches, love reveals.", "Unknown", "Life"),
//            QuotesData("Be yourself; everyone else is already taken.", "Oscar Wilde", "Famous"),
//            QuotesData("The heart understands what the mind can't explain.", "Unknown", "Emotions"),
//
//            QuotesData("A journey of a thousand miles begins with a single step.", "Lao Tzu", "Proverbs"),
//
//            QuotesData("Life is what happens when you're busy making other plans.", "John Lennon", "Life"),
//            QuotesData("Books give a soul to the universe.", "Plato", "Books"),
//            QuotesData("Storms make trees take deeper roots.", "Dolly Parton", "Famous"),
//
//
//            QuotesData("Every emotion is a message—listen closely.", "Unknown", "Emotions"),
//            QuotesData("Proverbs are short sentences drawn from long experience.", "Miguel de Cervantes", "Proverbs"),
//            QuotesData("The more you read, the more you know.", "Unknown", "Books"),
//            QuotesData("Life becomes easier when you stay calm.", "Unknown", "Life"),
//
//            QuotesData("Vo Mera Nabi","Qazi Amaan","Maintaility"),
//            QuotesData("Famous people aren't great; great people become famous.", "Unknown", "Famous"),
//            QuotesData("Your emotions write the truth your words hide.", "Unknown", "Emotions"),
//            QuotesData("Wisdom begins in wonder.", "Socrates", "Proverbs"),
//            QuotesData("A book is a dream you hold in your hands.", "Neil Gaiman", "Books")
//        )


        val quotesList = mutableListOf(

            // Proverbs
            QuotesData("جو بوؤ گے وہی کاٹو گے۔", "مولانا روم", "Proverbs"),
            QuotesData("سچ تلخ ہوتا ہے مگر فائدہ دیتا ہے۔", "شیخ سعدی", "Proverbs"),
            QuotesData("وقت کی قدر کرو، ورنہ وقت تمہاری قدر نہیں کرے گا۔", "امام غزالی", "Proverbs"),
            QuotesData("محنت کبھی ضائع نہیں جاتی۔", "نامعلوم", "Proverbs"),
            QuotesData("جو سوچو گے وہی بنو گے۔", "قلندر بابا اولیا", "Proverbs"),

            // Books
            QuotesData("کتابیں بہترین استاد ہوتی ہیں۔", "اشفاق احمد", "Books"),
            QuotesData("مطالعہ انسان کو مکمل بناتا ہے۔", "سر فرانسس بیکن", "Books"),
            QuotesData("جو کتابیں پڑھتا ہے وہ دنیا سمجھتا ہے۔", "ممتاز مفتی", "Books"),
            QuotesData("اچھی کتاب ایک زندگی بدل دیتی ہے۔", "نعیم بخاری", "Books"),
            QuotesData("کتابیں انسان کے ذہن کو روشن کرتی ہیں۔", "احمد فراز", "Books"),

            // Life
            QuotesData("زندگی وہی ہے جو لمحہ موجود میں ہے۔", "اشفاق احمد", "Life"),
            QuotesData("جو ہے وہ اچھا ہے، جو نہیں ہے وہ بھی اچھا ہے۔", "بانو قدسیہ", "Life"),
            QuotesData("زندگی کا اصل حسن سادگی میں ہے۔", "واصف علی واصف", "Life"),
            QuotesData("مشکلات زندگی کا حصہ ہیں، رکاوٹ نہیں۔", "قلندر بابا اولیا", "Life"),
            QuotesData("وقت بدلتا ہے مگر اصول نہیں۔", "جاوید چوہدری", "Life"),

            // Famous
            QuotesData("کامیابی بہادروں کو پسند کرتی ہے۔", "نیلسن منڈیلا", "Famous"),
            QuotesData("دنیا تمہیں وہی دیکھتی ہے جو تم اپنے بارے میں سوچتے ہو۔", "محمد علی جناح", "Famous"),
            QuotesData("محنت وہ چیز ہے جو قسمت بدل دیتی ہے۔", "ابراہیم لنکن", "Famous"),
            QuotesData("بڑا سوچو، بڑا کرو، بڑا بنو۔", "شیخ چلی (مزاحیہ), مشہور قول", "Famous"),
            QuotesData("موقعے ان کے پاس آتے ہیں جو ان کے لیے تیار ہوتے ہیں۔", "ٹام ایڈیسن", "Famous"),

            // Emotions
            QuotesData("دل کی آواز کبھی جھوٹی نہیں ہوتی۔", "احمد فراز", "Emotions"),
            QuotesData("محبت خاموش بھی ہو تو محسوس ہوتی ہے۔", "پروین شاکر", "Emotions"),
            QuotesData("جذبات انسان کی پہچان ہوتے ہیں۔", "جون ایلیا", "Emotions"),
            QuotesData("دل وہی صحیح سمجھتا ہے جو آنکھیں نہیں دیکھ سکتیں۔", "علامہ اقبال", "Emotions"),
            QuotesData("رشتے احساس سے جیتے ہیں لفظوں سے نہیں۔", "نامعلوم", "Emotions"),

            // Maintaility
            QuotesData("سوچ بدل دو، زندگی بدل جائے گی۔", "واصف علی واصف", "Maintaility"),
            QuotesData("انسان وہی بنتا ہے جو وہ سوچتا ہے۔", "شیخ سعدی", "Maintaility"),
            QuotesData("نیت صاف ہو تو راستے خود بن جاتے ہیں۔", "مولانا طارق جمیل", "Maintaility"),
            QuotesData("اصل طاقت ذہن کی ہوتی ہے، جسم کی نہیں۔", "قلندر بابا اولیا", "Maintaility"),
            QuotesData("اونچا وہی اٹھتا ہے جو سوچ اونچی رکھتا ہے۔", "اشفاق احمد", "Maintaility")
        )




        adapter = QoutesAdapter(quotesList.toMutableList())
        binding.strecycleview.adapter = adapter
        binding.strecycleview.layoutManager = GridLayoutManager(requireContext(), 2)



        adapter.setOnItemClickListener { quote, writer ->
            val fragment = Detailsfragment().apply {
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
            val filterQuotesData = if (category == "All") {
                quotesList
            } else {
                quotesList.filter { it.category.equals(category, ignoreCase = true) }
            }
adapter.updateData(filterQuotesData)

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
                    binding.btnMaintaility -> showFilteredQuotes("Maintaility")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun QoutesAdapter.updateData(filterQuotesData: List<QuotesData>) {
    this.updateData(filterQuotesData)

}

