package com.homework.homeworkkuritsyn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.ActivityMainBinding
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var checkFirstStartUseCase: CheckFirstStartUseCase
    private var appBarConfiguration by Delegates.notNull<AppBarConfiguration>()

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navigationView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.addInDefaultArgs(intent.extras)

        if(!checkFirstStartUseCase.execute()) {
            graph.setStartDestination(R.id.loansFragment)
        }

        navController.setGraph(graph, intent.extras)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loansFragment, R.id.applyFragment, R.id.manualFragment), drawerLayout
        )
        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun lockDrawer() {
        binding.drawerLayout.close()
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
    fun unLockDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}