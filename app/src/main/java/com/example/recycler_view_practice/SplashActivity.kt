package com.example.recycler_view_practice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import javax.net.ssl.HandshakeCompletedEvent

class SplashActivity  : AppCompatActivity(){
    private var delayHandler = Handler()
    private val SPLASH_DELAY : Long =3000
    internal val mRunnable : Runnable  = Runnable{
        if(!isFinishing){
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//
//        finish()

        delayHandler = Handler()
        delayHandler!!.postDelayed(mRunnable,SPLASH_DELAY)
    }

    override fun onDestroy() {
        if(delayHandler !=null){
            delayHandler!!.removeCallbacks(mRunnable)

        }

        super.onDestroy()

    }
}