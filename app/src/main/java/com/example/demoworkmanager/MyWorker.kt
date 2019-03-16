package com.example.demoworkmanager

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.work.Worker

class MyWorker: Worker() {
    var mHandler: Handler? = null

    override fun doWork(): WorkerResult {
        // Sleep for debugging purposes
        Thread.sleep(3000)

//        Looper.prepare()
        Log.d("MY","HELLO")
//        mHandler = object : Handler() {
//            override fun handleMessage(msg: Message) {
//                // process incoming messages here
//                Toast.makeText(applicationContext,"Hello",Toast.LENGTH_SHORT).show()
//            }
//        }
//        Looper.loop()
        mHandler= Handler(Looper.getMainLooper())
        mHandler!!.post { Toast.makeText(applicationContext,"Hello",Toast.LENGTH_SHORT).show() }
        return WorkerResult.SUCCESS
    }





}