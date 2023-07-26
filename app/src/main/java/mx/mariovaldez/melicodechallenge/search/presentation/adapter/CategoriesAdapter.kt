package mx.mariovaldez.melicodechallenge.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.mariovaldez.melicodechallenge.databinding.ViewCategoryItemBinding
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI

internal class CategoriesAdapter(
    private val isSelectable: Boolean = false,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val categories: MutableList<CategoriesUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            val itemSize = parent.measuredWidth / 2
            root.layoutParams.width = itemSize
            root.layoutParams.height = itemSize
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    fun addCategories(categories: List<CategoriesUI>) {
        this.categories.clear()
        this.categories.addAll(categories)
    }

    inner class ViewHolder(
        private val binding: ViewCategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoriesUI) = with(binding) {
            labelTextView.text = category.name
            containerCardView.setOnClickListener {
                listener(category.id)
            }
        }
    }
}
