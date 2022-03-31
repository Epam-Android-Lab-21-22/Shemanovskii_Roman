package com.github.beleavemebe.ui_basics_showcase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.beleavemebe.ui_basics_showcase.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeCounters()
        initListeners()
        initCardBalance()
        initCardNumbers()
        initClickCounter()
    }

    private fun initListeners() {
        binding.apply {
            etUsername.doOnTextChanged { _, _, _, _ ->
                viewModel.onCharacterEntered()
            }
            btnEdit.setOnClickListener {
                etUsername.forceUserToEdit()
            }
        }
    }

    private fun observeCounters() {
        viewModel.clickCounter.observe(this@MainActivity) {
            binding.tvClickCounter.text = it.toString()
        }

        viewModel.characterCounter.observe(this@MainActivity) {
            binding.tvCharsEntered.text = it.toString()
        }
    }

    private fun initCardBalance() {
        setOf(
            binding.tvCardBalance1,
            binding.tvCardBalance2,
            binding.tvCardBalance3,
            binding.tvCardBalance4,
            binding.tvDecemberSpendings,
            binding.tvDecemberCashback
        ).forEach { textView ->
            viewModel.fetchValueForTextView(textView.id)?.let { value ->
                textView.text = value
            } ?: textView.setRandomBalance()
        }
    }

    private fun initCardNumbers() {
        setOf(
            binding.tvCardNumbers1,
            binding.tvCardNumbers2,
            binding.tvCardNumbers3
        ).forEach { textView ->
            viewModel.fetchValueForTextView(textView.id)?.let {
                textView.text = it
            } ?: textView.setRandomCardNumber()
        }
    }

    private fun initClickCounter() {
        setOf(
            binding.cvRandomInfo1,
            binding.cvRandomInfo2,
            binding.cvCard1,
            binding.cvCard2,
            binding.cvCard3,
            binding.cvCard4,
            binding.cvClicksStats,
            binding.cvCharsStats,
            binding.btnNewCardOrAccount
        ).forEach {
            it.setOnClickListener {
                viewModel.onViewClicked()
            }
        }
    }

    private fun TextView.setRandomBalance() {
        fun Int.toThreeFiguredString(): String =
            "00$this".run {
                substring(lastIndex - 2, length)
            }

        val balance = Random.Default.nextInt(1000000)
        val str = if (balance >= 1000) "${balance / 1000} ${(balance % 1000).toThreeFiguredString()}" else "$balance"
        val value = getString(R.string.balance_placeholder, str)
        text = value
        viewModel.notifyValueWasSetForTextView(id, value)
    }

    private fun TextView.setRandomCardNumber() {
        val cardNumber = Random.Default.nextInt(1000, 10000)
        val value = getString(R.string.card_number_placeholder, cardNumber)
        text = value
        viewModel.notifyValueWasSetForTextView(id, value)
    }

    private fun EditText.forceUserToEdit() {
        requestFocus()
        setSelection(text.length)
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, 0)
    }
}
