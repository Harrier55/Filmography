package ru.harrier55.project.filmography.ui

/**
 * lesson 6 services and Broadcastresievers is completed. PullRequest OK
 * lesson 7 Retrofit and Glide is completed. PullRequest OK
 * lesson 8 Room and change UI interface. Pull Request OK
 * lesson 9 Permissions  Pull RequestOK
 * lesson 10 Google Maps Pull Request OK*/

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ru.harrier55.project.filmography.ui.googlemaps.MapsFragment
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.ui.broadcastresievers.MyBroadcastReceiver
import ru.harrier55.project.filmography.ui.favorit.FavoriteFragment
import ru.harrier55.project.filmography.ui.filmlist.FilmListFragment
import ru.harrier55.project.filmography.ui.rating.RatingsFragment

class MainActivity : AppCompatActivity() {

    private val TAG:String = "@@@"

    private lateinit var bottomNavigation: BottomNavigationView
    private var filmListFragment: FilmListFragment = FilmListFragment()
    private var favoritFragment: FavoriteFragment = FavoriteFragment()
    private var ratingsFragment: RatingsFragment = RatingsFragment()
    private var mapsFragment: MapsFragment = MapsFragment()

    private val targetPermissionGps: String = android.Manifest.permission.ACCESS_FINE_LOCATION
    private var REQUEST_CODE_PERMISSION_READ_CONTACTS: Int = 11


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        (applicationContext as MyApp).generateTestRepo(cardFilm)  // заполнить тестовый репозиторий


        initBottomNavigation()
        initFragmentManager(filmListFragment)

        /**регистрация приемника**/
        registerReceiver(MyBroadcastReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        /** Проверка PERMISSIONS*/
        checkedPermissionApplication()
    }


    private fun initBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.home_bottom_navigation -> {
                    initFragmentManager(filmListFragment)
                    true
                }
                R.id.favorit_bottom_navigation -> {
                    initFragmentManager(favoritFragment)
                    true
                }
                R.id.maps_bottom_navigation -> {
                    initFragmentManager(mapsFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun initFragmentManager(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        if(fragmentManager.backStackEntryCount == 0){
                  showClosingApp()
        }
 //       super.onBackPressed()
    }

    fun showClosingApp(){
        val contextView = findViewById<View>(R.id.fragment_container)
        Snackbar.make(contextView,R.string.close_App,Snackbar.LENGTH_SHORT)
            .setAnchorView(R.id.bottom_navigation)
            .setAction(R.string.yes) {
                finish()
            }
            .show()
    }

    override fun onDestroy() {
        unregisterReceiver(MyBroadcastReceiver())
        super.onDestroy()
    }

    /** Управляющий метод проверки разрешений*/
    private fun checkedPermissionApplication() {

        if(!isPermissionGranted(targetPermissionGps)){

            when(shouldShowRequestPermissionRationale(targetPermissionGps)){
                true -> requestPermission(targetPermissionGps)
                false -> showSimpleAlert()
            }
        }
    }

    /**  Проверяем разрешения*/
    private fun isPermissionGranted(permission: String): Boolean {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED   }

    /** Запрашиваем разрешения у системы */
    private fun requestPermission(permission: String) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(permission),
            REQUEST_CODE_PERMISSION_READ_CONTACTS
        )
    }

    /** Открываем настройки приложения программно*/
    private fun openAppSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.parse("package:$packageName")
        startActivityForResult(intent, REQUEST_CODE_PERMISSION_READ_CONTACTS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PERMISSION_READ_CONTACTS) {
            requestApplicationConfig();
        }
    }

    private fun requestApplicationConfig() {

        if (isPermissionGranted(targetPermissionGps)) {
            Toast.makeText(this, "Теперь разрешения получены", Toast.LENGTH_LONG)
                .show()
        } else {
            requestPermission(targetPermissionGps)

            if (isPermissionGranted(targetPermissionGps)) {
                Toast.makeText(this, "Теперь разрешения получены", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Пользователь снова не дал нам разрешение",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    /** Диалог о необходимости открытия разрешений */
    private fun showSimpleAlert() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.request_uses_GPS))
            .setMessage(
                getString(R.string.description_request_uses_Gps)
            )
            .setPositiveButton("OK") { dialog, which ->
                openAppSettings()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                requestApplicationConfig()
            }
            .show()
    }

}