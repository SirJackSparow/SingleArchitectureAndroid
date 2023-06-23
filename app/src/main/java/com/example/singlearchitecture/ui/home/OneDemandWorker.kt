package com.example.singlearchitecture.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.SystemClock.sleep
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.singlearchitecture.R

class OneDemandWorker(ctx: Context, workParams:WorkerParameters) : CoroutineWorker(ctx, workParams) {

    override suspend fun doWork(): Result {
       val appContext = applicationContext
        createNotificationChannel(appContext)

        return try {
            val res = dummyWorks()
            val outputData = workDataOf("RESP" to res)
            Log.e("tag", outputData.toString() )
            Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e("tag", throwable.toString() )
            Result.failure()
        }
    }

    private suspend fun dummyWorks() :  String {
        sleep(2000)
        return "Completed task"
    }

    private fun createNotificationChannel(context: Context) {
        var notification = NotificationCompat.Builder(context, "202")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("titles")
            .setContentText("desc")
            .build()

    }

}