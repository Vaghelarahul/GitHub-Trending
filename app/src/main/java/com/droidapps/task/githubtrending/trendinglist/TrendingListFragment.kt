package com.droidapps.task.githubtrending.trendinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.droidapps.task.githubtrending.databinding.FragmentTrendingListBinding
import com.droidapps.task.githubtrending.network.Trending

/**
 * Created By Rahul Vaghela on 16/7/19
 */
class TrendingListFragment : Fragment() {

    private lateinit var selectedItem: Trending

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentTrendingListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProviders.of(this).get(TrendingListViewModel::class.java)
        binding.viewModel = viewModel

        val adapter = TrendingListAdapter(object : TrendingListAdapter.ItemClickListener {
            override fun onItemClick(trending: Trending) {
                selectedItem = trending
                viewModel.navigateToDetailPage()
            }
        })

        binding.recyclerView.adapter = adapter

        viewModel.trendingList.observe(this, Observer { trendingList ->
            adapter.submitList(trendingList)
        })

        viewModel.navigateToDetails.observe(this, Observer {
            if (it) {
                findNavController().navigate(
                    TrendingListFragmentDirections.actionTrendingListFragmentToTrendingItemDetail(
                        selectedItem
                    )
                )
                viewModel.doneNavigatingToDetailPage()
            }
        })

        return binding.root
    }

}