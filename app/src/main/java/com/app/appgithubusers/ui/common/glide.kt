package com.app.appgithubusers.ui.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun glideSetImage(context: Context, url: String?, intoImg: ImageView){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .circleCrop()
        .into(intoImg)
}