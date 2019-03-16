package com.example.demoworkmanager

import android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
import android.annotation.TargetApi
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager

import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(){



    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val myConstraints = Constraints.Builder()
            .setRequiresDeviceIdle(true)
            .setRequiresCharging(true)
            .build()
        fab.setOnClickListener { view ->
           /* val request = OneTimeWorkRequest.Builder(MyWorker::class.java)
//                .setConstraints(myConstraints)
                .build()*/
            val request = PeriodicWorkRequest.Builder(MyWorker::class.java,1,TimeUnit.MINUTES)
//                .setConstraints(myConstraints)
                .build()
//            WorkManager.getInstance().enqueue(request)


            /*  WorkManager.getInstance()
                .getStatusById(request.id)
                .observe(this@MainActivity, Observer {
                    it?.let {
                        // Get the output data from the worker.
                        val workerResult = it.outputData
                        // Check if the task is finished?
                        if (it.state.isFinished) {
                            Toast.makeText(this, "Work completed.", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "Work failed.", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                })*/

            val dialog=DialogOpen(this)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
