package com.example.playlistmaker
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.playlistmaker.MusicActivity
import com.example.playlistmaker.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastSearch = findViewById<Button>(R.id.searchButton)

        val iconClickListener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(
                    this@MainActivity,
                    "Здесь пока ничего нет,\nНо это только пока))",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        toastSearch.setOnClickListener(iconClickListener)


        val toastMusic = findViewById<Button>(R.id.musicButton)

        toastMusic.setOnClickListener {
            Toast.makeText(this@MainActivity, "Здесь тоже пока ничего нет", Toast.LENGTH_SHORT)
                .show()
        }

        val searchButton = findViewById<Button>(R.id.searchButton)


        searchButton.setOnClickListener {
            val searchActivityIntent = Intent(this, SearchActivity::class.java)
            startActivity(searchActivityIntent)
        }

        val musicButton = findViewById<Button>(R.id.musicButton)


        musicButton.setOnClickListener {
            val musicActivityIntent = Intent(this, MusicActivity::class.java)
            startActivity(musicActivityIntent)
        }

        val settingButton = findViewById<Button>(R.id.settingButton)


        settingButton.setOnClickListener {
            val settingIntent = Intent(this, SettingActivity::class.java)
            startActivity(settingIntent)
        }
    }
}

