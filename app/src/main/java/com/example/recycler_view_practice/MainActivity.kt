package com.example.recycler_view_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.widget.ActionMenuView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recycler_view_practice.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.temporal.TemporalAccessor

class MainActivity : AppCompatActivity() {

    val Tag : String = "로그"

     var homefragment: homeFragment  = homeFragment()
     var playFragment: playFragment = playFragment()
     var listfragment: listFragment = listFragment()
    lateinit var binding : ActivityMainBinding

    //추천음악을 만든 배열이된다.
    var recommendList = ArrayList<RecommendModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        replaceFragment(homefragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationiItemSelectedListener)
        setContentView(binding.root)

        Log.d(Tag, "MainActivity - OnCreate() called")
        Log.d(Tag, "MainActivity - 반복문 돌리기 전 this.recommendList.size : ${this.recommendList.size}() called")
         for (i in 1..10)
         {
             var recommendModel  = RecommendModel(0, "음악","가수")
             this.recommendList.add(recommendModel)

         }

        Log.d(Tag, "MainActivity - 반복문 돌린 후 this.recommendList.size : ${this.recommendList.size}() called")



    }



    private val mOnNavigationiItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.action_home -> {
                println("home pressed")
                replaceFragment(homefragment)
                Log.d(Tag,"homeFragment() 출력")
                return@OnNavigationItemSelectedListener true
            }

            R.id.action_play  -> {
                println("test pressed")
                replaceFragment(playFragment)
                Log.d(Tag,"playFragment() 출력")

                return@OnNavigationItemSelectedListener true
            }

            R.id.action_playlist -> {
                println("info pressed")
                replaceFragment(listfragment)
                Log.d(Tag,"listFragment() 출력")

                return@OnNavigationItemSelectedListener true
            }

            else -> false
        }
    }
    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}