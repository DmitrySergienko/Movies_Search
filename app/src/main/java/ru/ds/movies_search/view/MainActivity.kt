package ru.ds.movies_search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ds.movies_search.R
import ru.ds.movies_search.databinding.MainActivityBinding
import ru.ds.movies_search.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bind:MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = MainActivityBinding.inflate(layoutInflater)
        setContentView(bind.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit()
        }
    }



}