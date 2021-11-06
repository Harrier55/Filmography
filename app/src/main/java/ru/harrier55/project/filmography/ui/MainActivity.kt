package ru.harrier55.project.filmography.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.CardFilmRepoImpl
import ru.harrier55.project.filmography.data.MyApp

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation:BottomNavigationView

    private var homeFragment:HomeFragment = HomeFragment()
    private  var favoritFragment: FavoritFragment = FavoritFragment()
    private var ratingsFragment: RatingsFragment = RatingsFragment()

    private  var cardFilm = CardFilm()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApp).generateTestRepo(cardFilm)  // заполнить тестовый репозиторий

        initBottomNavigation()

        val fragmentManager:FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container,homeFragment)
            .commit()
    }





    private fun initBottomNavigation(){
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { itemMenu ->
            val fragmentManager:FragmentManager = supportFragmentManager
            when(itemMenu.itemId){
                R.id.home_bottom_navigation ->{
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, homeFragment)
                        .commit()
                    true
                }

                R.id.favorit_bottom_navigation ->{
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, favoritFragment)
                        .commit()
                    true
                }
                R.id.ratings_bottom_navigation -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ratingsFragment)
                        .commit()
                    true
                }
                else -> {
                   true
                }
            }
        }
    }



}