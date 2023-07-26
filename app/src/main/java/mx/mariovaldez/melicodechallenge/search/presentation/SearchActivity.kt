package mx.mariovaldez.melicodechallenge.search.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.melicodechallenge.R
import mx.mariovaldez.melicodechallenge.databinding.ActivitySearchBinding
import mx.mariovaldez.melicodechallenge.ktx.observe
import mx.mariovaldez.melicodechallenge.ktx.viewBinding

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels()
    private val binding: ActivitySearchBinding by viewBinding(
        ActivitySearchBinding::inflate
    )

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAppClosingDialog()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.event.observe(this, ::handle)
    }

    private fun handle(event: SearchViewModel.Event) {
        if (event.type == SearchViewModel.EventType.NAVIGATION) {
            handleNavigation(event)
        } else {
            // Another type of event.
        }
    }

    private fun setupViews() {
        disableAutoFill()
    }

    private fun disableAutoFill() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window?.decorView?.importantForAutofill =
                View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
    }

    private fun handleNavigation(event: SearchViewModel.Event) {
        val fragment: Fragment = when (event) {
            is SearchViewModel.Event.NavigateToProductListRequirement -> ProductListFragment.newInstance(
                event.query
            )

            is SearchViewModel.Event.NavigateToSearchRequirement -> SearchProductFragment.newInstance()
            is SearchViewModel.Event.NavigateToProductDetailRequirement -> ProductDetailFragment.newInstance(
                event.productUI
            )
        }

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(
                R.id.fragment_container_view,
                fragment,
                fragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }

    private fun showAppClosingDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Warning")
            .setMessage("Do you really want to close the app?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, SearchActivity::class.java))
    }
}
