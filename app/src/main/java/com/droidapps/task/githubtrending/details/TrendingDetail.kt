package com.droidapps.task.githubtrending.details

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.droidapps.task.githubtrending.databinding.FragmentTrendingDetailBinding

/**
 * Created By Rahul Vaghela on 16/7/19
 */
class TrendingDetail : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentTrendingDetailBinding.inflate(inflater)

        val trendingArgs = TrendingDetailArgs.fromBundle(arguments!!)

        binding.trending = trendingArgs.trending
        binding.executePendingBindings()

        return binding.root
    }
}