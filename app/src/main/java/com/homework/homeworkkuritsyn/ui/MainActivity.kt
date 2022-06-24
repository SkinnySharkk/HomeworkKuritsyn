package com.homework.homeworkkuritsyn.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
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
import com.homework.homeworkkuritsyn.presenters.MainViewModel
import javax.inject.Inject
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private companion object {
        const val ID_DESTINATION = "ID"
    }

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private var appBarConfiguration by Delegates.notNull<AppBarConfiguration>()

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.appComponent.mainComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.addInDefaultArgs(intent.extras)

        if (viewModel.checkIsFirstStart()) {
            graph.setStartDestination(R.id.loansFragment)
        }
        if (savedInstanceState != null) {
            graph.setStartDestination(savedInstanceState.getInt(ID_DESTINATION))
        }
        navController.setGraph(graph, intent.extras)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loansFragment, R.id.applyFragment, R.id.manualFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val currentDestinationId = findNavController(R.id.navHostFragment).currentDestination?.id
        if (currentDestinationId != null) {
            outState.putInt(ID_DESTINATION, currentDestinationId)
        }
        super.onSaveInstanceState(outState)
    }
}