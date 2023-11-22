package com.example.playlistmaker

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
import com.example.playlistmaker.R

class SearchActivity : AppCompatActivity() {
    companion object {
        const val PRODUCT_AMOUNT = "PRODUCT_AMOUNT"
        const val AMOUNT_DEF = " "
        private var countValue: String = AMOUNT_DEF
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val settingButton = findViewById<Button>(R.id.backSearch)

        settingButton.setOnClickListener() {
            onBackPressed()
        }

        val editTextSearch: EditText = findViewById(R.id.editTextSearch)
        val clearButton: ImageButton = findViewById(R.id.ClearButton)


        if (savedInstanceState != null) {
            // Восстановите значение из savedInstanceState
            countValue = savedInstanceState.getString(PRODUCT_AMOUNT, AMOUNT_DEF)
            editTextSearch.setText(countValue)
        }

        editTextSearch.setOnClickListener {
            // При нажатии на кнопку поиска устанавливаем сохраненное значение в EditText
            editTextSearch.setText(countValue)
        }






        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isNightMode = currentNightMode == Configuration.UI_MODE_NIGHT_YES



        val hint = "Поиск"
        val spannable = SpannableString(hint)
        val colorResId = if (isNightMode) R.color.black else R.color.greyText
        val color = ResourcesCompat.getColor(resources, colorResId, theme)
        spannable.setSpan(ForegroundColorSpan(color), 0, hint.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        editTextSearch.hint = spannable

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.visibility = if (s.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
            }
        })
    }

    fun clear(view: View) {
        val editTextSearch: EditText = findViewById(R.id.editTextSearch)
        editTextSearch.text.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editTextSearch: EditText = findViewById(R.id.editTextSearch)
        // Сохраните текущее значение из EditText
        countValue = editTextSearch.text.toString()
        outState.putString(PRODUCT_AMOUNT, countValue)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Вторым параметром мы передаём значение по умолчанию
        countValue = savedInstanceState.getString(PRODUCT_AMOUNT, AMOUNT_DEF)
    }
}
