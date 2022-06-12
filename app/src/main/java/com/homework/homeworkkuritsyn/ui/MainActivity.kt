package com.homework.homeworkkuritsyn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.ActivityMainBinding
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var checkFirstStartUseCase: CheckFirstStartUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.addInDefaultArgs(intent.extras)

        if(checkFirstStartUseCase.execute()) {
            graph.setStartDestination(R.id.startFragment)
        } else {
            graph.setStartDestination(R.id.loansFragment)
        }
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}