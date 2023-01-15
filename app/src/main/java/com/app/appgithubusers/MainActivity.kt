package com.app.appgithubusers

import android.os.Bundle
import android.view.LayoutInflater
import com.app.appgithubusers.databinding.ActivityMainBinding
import com.app.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :  BaseActivity<ActivityMainBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
    }
}