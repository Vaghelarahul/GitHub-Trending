package com.droidapps.task.githubtrending.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By Rahul Vaghela on 17/7/19
 */

private const val BASE_URL = "https://github-trending-api.now.sh/"

interface ApiService {
    @GET("developers")
    fun getGitHubTrending(@Query("language") language: String, @Query("since") since: String): Deferred<List<Trending>>
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object NetworkService {
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

