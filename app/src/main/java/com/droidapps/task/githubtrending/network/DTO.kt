package com.droidapps.task.githubtrending.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created By Rahul Vaghela on 17/7/19
 */

@Parcelize
data class Trending(
    @SerializedName("username") var username: String,
    @SerializedName("name") var name: String,
    @SerializedName("type") var type: String,
    @SerializedName("url") var url: String,
    @SerializedName("avatar") var avatar: String,
    @SerializedName("repo") var repo: Repo
) : Parcelable

@Parcelize
data class Repo(
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String
) : Parcelable