package com.example.playlistmaker

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.playlistmaker.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
        val settingButton = findViewById<Button>(R.id.back)

        settingButton.setOnClickListener() {
            onBackPressed()
        }

        val switch_btn = findViewById<Switch>(R.id.themeSwitch)
        val sharedPreferences = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)

        val isNightModeOn: Boolean = sharedPreferences.getBoolean("is_dark_theme", false)

        switch_btn.isChecked = false

        switch_btn.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("is_dark_theme", isChecked).apply()
        }



        switch_btn.setOnClickListener {
            val newNightModeState = switch_btn.isChecked
            sharedPreferences.edit().putBoolean("is_dark_theme", newNightModeState).apply()
            if (!newNightModeState) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

        }

        val supportButton: Button = findViewById(R.id.buttonSupport)
        supportButton.setOnClickListener {
            openEmailClient()
        }
        val offer = getString(R.string.Offer)
        val agreementButton: Button = findViewById(R.id.buttonAgreement)
        agreementButton.setOnClickListener {
            openWebPage(offer)
        }

        val shareAppButton: Button = findViewById(R.id.buttonShare)
        shareAppButton.setOnClickListener {
            shareContent(getString(R.string.ShareUrl))
        }
    }


    private fun shareContent(url: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$url")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


    private fun openWebPage(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun openEmailClient() {
        val recipientEmail =
            "s.u-2000@yandex.ru"
        val subject = "Сообщение разработчикам и разработчицам приложения Playlist Maker"
        val message = "Спасибо разработчикам и разработчицам за крутое приложение!"

        val intent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:")
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }
        startActivity(intent)
    }
}






