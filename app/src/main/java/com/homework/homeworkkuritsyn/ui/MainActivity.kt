package com.homework.homeworkkuritsyn.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.ActivityMainBinding
import com.homework.homeworkkuritsyn.presenters.MainViewModel
import com.homework.homeworkkuritsyn.ui.authorization.LoginFragmentDirections
import javax.inject.Inject
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

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
        if (viewModel.isAuthorized()) {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToLoansFragment())
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility =
                if (destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.loansFragment,
                R.id.applyFragment,
                R.id.manualFragment,
                R.id.profileFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}