package ru.harrier55.project.filmography.ui

/**
 *
 * */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.MyApp

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private var filmListFragment: FilmListFragment = FilmListFragment()
    private var favoritFragment: FavoritFragment = FavoritFragment()
    private var ratingsFragment: RatingsFragment = RatingsFragment()
    private var cardFilm = CardFilm()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApp).generateTestRepo(cardFilm)  // заполнить тестовый репозиторий

        initBottomNavigation()
        initFragmentmandger(filmListFragment)

    }


    private fun initBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.home_bottom_navigation -> {
                    initFragmentmandger(filmListFragment);true
                }
                R.id.favorit_bottom_navigation -> {
                    initFragmentmandger(favoritFragment);true
                }
                R.id.ratings_bottom_navigation -> {
                    initFragmentmandger(ratingsFragment);true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun initFragmentmandger(fragment: Fragment) {
        var fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


}