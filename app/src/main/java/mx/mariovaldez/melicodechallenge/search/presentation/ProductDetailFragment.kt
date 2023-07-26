package mx.mariovaldez.melicodechallenge.search.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.melicodechallenge.R
import mx.mariovaldez.melicodechallenge.databinding.FragmentProductDetailBinding
import mx.mariovaldez.melicodechallenge.ktx.context
import mx.mariovaldez.melicodechallenge.ktx.exhaustive
import mx.mariovaldez.melicodechallenge.ktx.gone
import mx.mariovaldez.melicodechallenge.ktx.hideKeyboard
import mx.mariovaldez.melicodechallenge.ktx.observe
import mx.mariovaldez.melicodechallenge.ktx.parseCurrency
import mx.mariovaldez.melicodechallenge.ktx.parseToDiscountFormat
import mx.mariovaldez.melicodechallenge.ktx.parseToPercent
import mx.mariovaldez.melicodechallenge.ktx.viewBinding
import mx.mariovaldez.melicodechallenge.search.presentation.ProductDetailViewModel.Companion.PRODUCT_EXTRA
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val viewModel: ProductDetailViewModel by viewModels()
    private val binding: FragmentProductDetailBinding by viewBinding(
        FragmentProductDetailBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
        viewModel.showData()
    }

    private fun setupViews() {
        hideKeyboard()
        with(binding.toolbar) {
            searchTextInputLayout.gone()
            backButton.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: ProductDetailViewModel.State) {
        when (state) {
            is ProductDetailViewModel.State.ShowData -> showData(state.product)
            else -> {
                // This is an error state
            }
        }.exhaustive
    }

    private fun showData(product: ProductUI) {
        with(binding) {
            productNameTextView.text = product.title
            productPriceTextView.text = parseCurrency(product.price)
            if (product.installmentsUI != null) {
                productMsiTextView.text = String.format(
                    context.getString(R.string.msi),
                    product.installmentsUI.quantity,
                    product.installmentsUI.amount.toDouble()
                )
            }
            if (product.originalPrice != null && product.originalPrice != 0) {
                productOriginalPriceTextView.text = parseCurrency(product.originalPrice)
                productDiscountTextView.text =
                    parseToDiscountFormat(
                        parseToPercent(
                            product.price,
                            product.originalPrice
                        )
                    )
            }
            Glide.with(requireContext()).load(product.thumbnail)
                .placeholder(R.drawable.icv_logo).into(imageView)
        }
    }

    companion object {

        fun newInstance(product: ProductUI): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = bundleOf().apply {
                    putSerializable(PRODUCT_EXTRA, product)
                }
            }
        }
    }
}
