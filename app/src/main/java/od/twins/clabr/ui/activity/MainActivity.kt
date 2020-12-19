package od.twins.clabr.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import od.twins.clabr.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            run {
                when (destination.id) {
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_info -> nav_view.visibility =
                        View.VISIBLE
                    else -> nav_view.visibility = View.GONE
                }
            }
        }
        setupBottomNav(navigationController)
    }

    private fun setupBottomNav(navigationController: NavController) {
        nav_view?.let { NavigationUI.setupWithNavController(it, navigationController) }
    }
}
