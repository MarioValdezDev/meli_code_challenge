package mx.mariovaldez.melicodechallenge.search.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.mariovaldez.melicodechallenge.R
import mx.mariovaldez.melicodechallenge.databinding.ItemProductBinding
import mx.mariovaldez.melicodechallenge.ktx.context
import mx.mariovaldez.melicodechallenge.ktx.parseCapitalizedString
import mx.mariovaldez.melicodechallenge.ktx.parseCurrency
import mx.mariovaldez.melicodechallenge.ktx.parseToDiscountFormat
import mx.mariovaldez.melicodechallenge.ktx.parseToPercent
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI

internal class ProductListAdapter(
    val activity: Activity,
    private val listener: (ProductUI) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products: MutableList<ProductUI> = mutableListOf()

    var searching = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProductListAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun addProducts(products: List<ProductUI>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductUI) {
            with(binding) {
                root.setOnClickListener {
                    listener(product)
                }
                productNameTextView.text = product.title
                Glide.with(itemView.context).load(product.thumbnail)
                    .placeholder(R.drawable.icv_logo).into(imageView)
                productShippingTextView.text =
                    if (product.shipping.freeShipping) context.getString(R.string.free_shipping) else ""
                productPriceTextView.text = parseCurrency(product.price)
                productDiscountTextView.text =
                    if (product.originalPrice != null &&
                        product.originalPrice != 0
                    ) {
                        parseToDiscountFormat(
                            parseToPercent(
                                product.price,
                                product.originalPrice
                            )
                        )
                    } else {
                        ""
                    }
                if (product.installmentsUI != null) {
                    productMsiTextView.text = String.format(
                        context.getString(R.string.msi),
                        product.installmentsUI.quantity,
                        product.installmentsUI.amount.toDouble()
                    )
                }
                productSellerTextView.text =
                    String.format(
                        context.getString(R.string.seller_name),
                        product.seller.name.parseCapitalizedString()
                    )
            }
        }
    }
}
