package com.droidapps.task.githubtrending.utils

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created By Rahul Vaghela on 17/7/19
 */


@BindingAdapter("showLoading")
fun ProgressBar.showLoading(isLoading: Boolean) {
    visibility = if (isLoading) View.VISIBLE else View.GONE
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("updateText")
fun TextView.updateText(value: String) {
    setText(if (TextUtils.isEmpty(value)) "NA" else value)
}