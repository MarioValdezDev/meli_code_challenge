package mx.mariovaldez.melicodechallenge.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.melicodechallenge.databinding.ActivitySearchBinding
import mx.mariovaldez.melicodechallenge.ktx.viewBinding

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val binding: ActivitySearchBinding by viewBinding(
        ActivitySearchBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, SearchActivity::class.java))
    }
}
