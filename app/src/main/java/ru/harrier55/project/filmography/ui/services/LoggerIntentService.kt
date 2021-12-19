package ru.harrier55.project.filmography.ui.services

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import java.io.File

class LoggerIntentService : IntentService("LoggerIntentService") {

    private val TAG = "@@@"

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: ")
        val event = intent?.getStringExtra("event")
        val currentDate = intent?.getStringExtra("currentDate")
        val file: File = intent?.getSerializableExtra("file") as File

        file.appendText("$event $currentDate")
        file.appendText("\n")

    }

}