package ru.harrier55.project.filmography.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import ru.harrier55.project.filmography.ui.services.LoggerIntentService
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object MyAnalytic {

    private val TAG = "@@@"
    private const val fileName = "log_application.txt"

    @SuppressLint("SimpleDateFormat")
    fun writeLogFile(context: Context, event: String){
        val fileObject: File = File(context.filesDir, fileName)
        val intent = Intent(context, LoggerIntentService::class.java)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate: String = sdf.format(Date())

        intent.putExtra("event", event)
        intent.putExtra("currentDate",currentDate)
        intent.putExtra("file",fileObject)
        context.startService(intent)
    }

    fun getFileObject(context: Context):File{
        return File(context.filesDir,fileName)
    }

    fun deleteLogFile(file: File){
        file.delete()
    }

}