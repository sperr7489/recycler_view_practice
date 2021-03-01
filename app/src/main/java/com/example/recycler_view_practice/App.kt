package com.example.recycler_view_practice

import android.app.Application


//context를 싱글턴 패턴으로 만든것이다.
class App :Application() {

    companion object{
        lateinit var instance :App
        //자기 자신을 가져오는 것.
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}