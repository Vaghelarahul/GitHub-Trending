package com.droidapps.task.githubtrending.trendinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidapps.task.githubtrending.network.Trending
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created By Rahul Vaghela on 17/7/19
 */
class TrendingListViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var trendingList = MutableLiveData<List<Trending>>()

    private val _navigateToDetails = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean>

    val navigateToDetails: LiveData<Boolean>
        get() = _navigateToDetails

    private val repository = TrendingListRepository()

    init {
        _navigateToDetails.value = false
        isLoading = repository.isFetched
        fetchTrendingList()
    }

    private fun fetchTrendingList() {
        viewModelScope.launch {
            trendingList.value = repository.fetchTrendingLists().value
        }
    }

    fun navigateToDetailPage() {
        _navigateToDetails.value = true
    }

    fun doneNavigatingToDetailPage() {
        _navigateToDetails.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}