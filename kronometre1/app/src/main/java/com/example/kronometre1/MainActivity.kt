package com.example.kronometre1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.kronometre1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number = 0

    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun start(view : View){
        number = 0

        runnable = object : Runnable{
            override fun run() {
               number = number + 1
               binding.textView.text ="Time: ${number}"
                handler.postDelayed(runnable,1000) // Kaç saniyede bir
            }

        }
        handler.post(runnable) // Runnable'ı başlatmak

        binding.button.isEnabled = false // Bu sayede Button'a tıklandıktan sonra tekrar tıklanamaz
    }

    fun stop(view: View){
        binding.button.isEnabled = true
        number = 0
        binding.textView.text = "Time: 0"
        handler.removeCallbacks(runnable) // Runnable'ı durdurmak
    }
}