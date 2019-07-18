package com.droidapps.task.githubtrending.trendinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droidapps.task.githubtrending.R
import com.droidapps.task.githubtrending.databinding.TrendingListItemBinding
import com.droidapps.task.githubtrending.network.Trending

/**
 * Created By Rahul Vaghela on 17/7/19
 */
class TrendingListAdapter(private val itemClickListener: ItemClickListener) :
    ListAdapter<Trending, TrendingListAdapter.TrendingViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trending = getItem(position)
        holder.bind(trending)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(trending)
        }
    }

    class TrendingViewHolder(private val binding: TrendingListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trending: Trending) {
            binding.trending = trending
            binding.executePendingBindings()
        }

        companion object {
            fun inflate(parent: ViewGroup): TrendingViewHolder {

                val binding = DataBindingUtil.inflate<TrendingListItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.trending_list_item,
                    parent,
                    false
                )
                return TrendingViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Trending>() {
        override fun areItemsTheSame(oldItem: Trending, newItem: Trending): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Trending, newItem: Trending): Boolean {
            return oldItem.repo.name == newItem.repo.name
        }
    }

    interface ItemClickListener {
        fun onItemClick(trending: Trending)
    }
}