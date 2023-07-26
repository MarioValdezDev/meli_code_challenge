package mx.mariovaldez.melicodechallenge.search.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.melicodechallenge.R
import mx.mariovaldez.melicodechallenge.databinding.FragmentProductListBinding
import mx.mariovaldez.melicodechallenge.ktx.exhaustive
import mx.mariovaldez.melicodechallenge.ktx.hideKeyboard
import mx.mariovaldez.melicodechallenge.ktx.invisible
import mx.mariovaldez.melicodechallenge.ktx.observe
import mx.mariovaldez.melicodechallenge.ktx.viewBinding
import mx.mariovaldez.melicodechallenge.ktx.visible
import mx.mariovaldez.melicodechallenge.search.presentation.ProductListViewModel.Companion.QUERY_EXTRA
import mx.mariovaldez.melicodechallenge.search.presentation.adapter.ProductListAdapter
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private val viewModel: ProductListViewModel by viewModels()
    private val binding: FragmentProductListBinding by viewBinding(
        FragmentProductListBinding::bind
    )
    private val activityViewModel: SearchViewModel by activityViewModels()
    private lateinit var productAdapter: ProductListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        viewModel.search()
    }

    private fun setupViews() {
        hideKeyboard()
        with(binding.toolbar) {
            backButton.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            searchTextInputEditText.isFocusableInTouchMode = false
            searchTextInputEditText.setOnClickListener {
                if (viewModel.productsIsEmpty()) {
                    parentFragmentManager.popBackStack()
                }
                activityViewModel.onRequirementCompleted(
                    SearchViewModel.Event.NavigateToSearchRequirement()
                )
            }
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(this, ::handle)
    }

    private fun handle(state: ProductListViewModel.State?) {
        when (state) {
            is ProductListViewModel.State.ShowData -> setupRecycler(state.data)
            ProductListViewModel.State.Error -> showNotFound()
            ProductListViewModel.State.Loading -> showLoading()
            ProductListViewModel.State.NotFound -> showNotFound()
            else -> {
                // do nothing
            }
        }.exhaustive
    }

    private fun navigateToProductDetail(productUI: ProductUI) {
        activityViewModel.onRequirementCompleted(
            SearchViewModel.Event.NavigateToProductDetailRequirement(productUI)
        )
        setDefaultState()
    }

    private fun showNotFound() {
        hideLoading()
        with(binding) {
            productsRecyclerView.invisible()
            itemNotFoundResult.container.visible()
        }
    }

    private fun setupRecycler(products: List<ProductUI>) {
        productAdapter = ProductListAdapter(requireActivity()) { product ->
            navigateToProductDetail(product)
        }.apply {
            addProducts(products)
        }
        with(binding.productsRecyclerView) {
            adapter = productAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }
        binding.productsRecyclerView.visible()
        hideLoading()
    }

    private fun hideLoading() {
        binding.Progress.invisible()
    }

    private fun showLoading() {
        with(binding) {
            productsRecyclerView.invisible()
            itemNotFoundResult.container.invisible()
            Progress.visible()
        }
    }

    private fun setDefaultState() {
        viewModel.setDefaultState()
    }

    companion object {

        fun newInstance(query: String) =
            ProductListFragment().apply {
                arguments = bundleOf(
                    QUERY_EXTRA to query
                )
            }
    }
}
