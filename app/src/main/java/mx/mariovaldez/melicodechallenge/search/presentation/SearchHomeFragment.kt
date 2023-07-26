package mx.mariovaldez.melicodechallenge.search.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.melicodechallenge.R
import mx.mariovaldez.melicodechallenge.databinding.FragmentSearchHomeBinding
import mx.mariovaldez.melicodechallenge.ktx.exhaustive
import mx.mariovaldez.melicodechallenge.ktx.gone
import mx.mariovaldez.melicodechallenge.ktx.observe
import mx.mariovaldez.melicodechallenge.ktx.showSmallLengthToast
import mx.mariovaldez.melicodechallenge.ktx.viewBinding
import mx.mariovaldez.melicodechallenge.search.presentation.adapter.CategoriesAdapter
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI
import mx.mariovaldez.melicodechallenge.ui.delegates.bind

@AndroidEntryPoint
class SearchHomeFragment : Fragment(R.layout.fragment_search_home) {

    private val viewModel: SearchHomeViewModel by viewModels()
    private val binding: FragmentSearchHomeBinding by viewBinding(
        FragmentSearchHomeBinding::bind
    )
    private val activityViewModel: SearchViewModel by activityViewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        viewModel.fetchCategories()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: SearchHomeViewModel.State?) {
        when (state) {
            is SearchHomeViewModel.State.Error -> showError()
            is SearchHomeViewModel.State.ShowData -> showData(state.data)
            null -> {}
        }.exhaustive
    }

    private fun showData(data: List<CategoriesUI>) {
        categoriesAdapter = CategoriesAdapter {
            requireContext().showSmallLengthToast("Próximamente")
        }.apply {
            addCategories(data)
        }
        with(binding.categoriesRecyclerView) {
            adapter = categoriesAdapter
        }
    }

    private fun showError() {
        requireContext().showSmallLengthToast("No se encontraron resultados")
    }

    private fun setupViews() {
        with(binding.toolbar) {
            bind { }
            searchTextInputEditText.isFocusableInTouchMode = false
            searchTextInputEditText.setOnClickListener {
                activityViewModel.onRequirementCompleted(
                    SearchViewModel.Event.NavigateToSearchRequirement()
                )
            }
            cancelButton.gone()
        }
    }
}
