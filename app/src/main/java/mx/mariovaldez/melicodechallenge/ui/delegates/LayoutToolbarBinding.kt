package mx.mariovaldez.melicodechallenge.ui.delegates

import mx.mariovaldez.melicodechallenge.databinding.LayoutToolbarBinding
import mx.mariovaldez.melicodechallenge.databinding.LayoutToolbarProductListBinding

internal fun LayoutToolbarBinding.bind(
    onBackPressed: () -> Unit
) {
    cancelButton.setOnClickListener { onBackPressed() }
}

internal fun LayoutToolbarProductListBinding.bind(
    onBackPressed: () -> Unit
) {
    backButton.setOnClickListener {
        onBackPressed()
    }
}
