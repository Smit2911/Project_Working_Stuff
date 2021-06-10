package op.mobile.app.dev.kaths4.travelling.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.kaths4.travelling.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.kaths4.travelling.model.Countries

class ServiceAdapter(private val listener: OnCountryClickListener) :
    ListAdapter<Countries, ServiceAdapter.ServiceViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Countries>() {
        override fun areItemsTheSame(
            oldItem: Countries,
            newItem: Countries
        ): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Countries,
            newItem: Countries
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceViewHolder {
        return ServiceViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: ServiceViewHolder,
        position: Int
    ) {
        val githubCountries = getItem(position)
        holder.bind(githubCountries)
    }

    inner class ServiceViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(githubJobs: Countries) {
            binding.countries = githubJobs
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onCountryClick(position)
            }
        }
    }

    interface OnCountryClickListener {
        fun onCountryClick(position: Int)
    }
}
