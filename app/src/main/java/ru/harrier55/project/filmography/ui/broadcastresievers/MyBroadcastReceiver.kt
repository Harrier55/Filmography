package ru.harrier55.project.filmography.ui.broadcastresievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import ru.harrier55.project.filmography.util.MyAnalytic

/** Проверяет состояние подключения к интернету*/

class MyBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            MyAnalytic.writeLogFile(context, "start class MyBroadcastReceiver")
        }
        checkConnected(context)
    }

    private fun checkConnected(context: Context?): Boolean {

        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (networkInfo == null) {
            MyAnalytic.writeLogFile(context, "Отключился интернет")
            Toast.makeText(
                context,
                "Отсутствует подключение к интернету",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            MyAnalytic.writeLogFile(context, "Интернет подключен")
        }
        return (networkInfo != null && networkInfo.isConnectedOrConnecting)
    }


}