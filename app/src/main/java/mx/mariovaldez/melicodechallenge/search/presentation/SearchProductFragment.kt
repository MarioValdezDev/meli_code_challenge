package mx.mariovaldez.melicodechallenge.search.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.melicodechallenge.R
import mx.mariovaldez.melicodechallenge.databinding.FragmentSearchProductBinding
import mx.mariovaldez.melicodechallenge.ktx.exhaustive
import mx.mariovaldez.melicodechallenge.ktx.hideKeyboard
import mx.mariovaldez.melicodechallenge.ktx.observe
import mx.mariovaldez.melicodechallenge.ktx.showKeyboard
import mx.mariovaldez.melicodechallenge.ktx.viewBinding

@AndroidEntryPoint
class SearchProductFragment : Fragment(R.layout.fragment_search_product) {

    private val activityViewModel: SearchViewModel by activityViewModels()
    private val viewModel: SearchProductViewModel by viewModels()

    private val binding: FragmentSearchProductBinding by viewBinding(
        FragmentSearchProductBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.event.observe(this, ::handle)
    }

    private fun handle(event: SearchProductViewModel.Event) {
        when (event) {
            is SearchProductViewModel.Event.Search -> {
                navigateToProductList(event.query)
            }
        }.exhaustive
    }

    private fun navigateToProductList(query: String) {
        activityViewModel.onRequirementCompleted(
            SearchViewModel.Event.NavigateToProductListRequirement(query)
        )
        parentFragmentManager.popBackStack()
    }

    private fun setupViews() {
        with(binding.toolbar) {
            searchTextInputEditText.setOnEditorActionListener { tv, actionId, _ ->
                when (actionId) {
                    android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH -> {
                        hideKeyboard()
                        viewModel.search(tv.text.toString())
                        true
                    }

                    else -> false
                }
            }
            startGuideline.setGuidelinePercent(.05f)
            endGuideline.setGuidelinePercent(.95f)
            cancelButton.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            searchTextInputEditText.requestFocus()
            showKeyboard(searchTextInputEditText)
        }
    }

    companion object {
        fun newInstance(): SearchProductFragment = SearchProductFragment()
    }
}
