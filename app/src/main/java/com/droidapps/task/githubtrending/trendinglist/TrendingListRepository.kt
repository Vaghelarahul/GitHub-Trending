package com.droidapps.task.githubtrending.trendinglist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.droidapps.task.githubtrending.network.NetworkService
import com.droidapps.task.githubtrending.network.Trending
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Rahul Vaghela on 17/7/19
 */

private const val LANGUAGE = "java"
private const val SINCE = "weekly"

private const val TAG = "TrendingListRepository"

class TrendingListRepository {

    private val list = MutableLiveData<List<Trending>>()

    private val _isFetched = MutableLiveData<Boolean>()
    val isFetched: LiveData<Boolean>
        get() = _isFetched

    suspend fun fetchTrendingLists(): MutableLiveData<List<Trending>> {
        _isFetched.value = true
        try {
            val deferredResponse = NetworkService.apiService.getGitHubTrending(LANGUAGE, SINCE)
            val response = deferredResponse.await()
            list.value = response

            _isFetched.value = false

        } catch (t: Throwable) {
            Log.i(TAG, "Api failure: ${t.message}")
            _isFetched.value = false
        }

        return list
    }
}