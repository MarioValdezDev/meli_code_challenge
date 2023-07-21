package mx.mariovaldez.melicodechallenge.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.mariovaldez.melicodechallenge.search.SearchActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(LAUNCH_DELAY)
            launchSearch()
        }
    }

    private fun launchSearch() {
        SearchActivity.launch(this)
        finish()
    }

    companion object {

        private const val LAUNCH_DELAY: Long = 1700
    }
}
