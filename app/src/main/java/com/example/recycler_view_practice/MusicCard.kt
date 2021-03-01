package com.example.recycler_view_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recycler_view_practice.databinding.ActivityMusicCardBinding

class MusicCard : AppCompatActivity() {

    private lateinit var binding : ActivityMusicCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

      binding = ActivityMusicCardBinding.inflate(layoutInflater)


      val intent= getIntent()
      binding.cardMusic.setImageResource(intent.getIntExtra("커버",0))
        Log.d("로그","intent가 정상적으로 들어옴.")
      binding.cardTitle.text =intent.getStringExtra("제목")
        Log.d("로그","intent가 정상적으로 들어옴.")
      binding.cardSinger.text = intent.getStringExtra("가수")
        Log.d("로그","intent가 정상적으로 들어옴.")


      setContentView(binding.root)


    }





}