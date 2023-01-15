package com.app.appgithubusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.app.appgithubusers.databinding.FragmentUserBinding
import com.app.appgithubusers.extension.formater
import com.app.appgithubusers.ui.common.glideSetImage
import com.app.base.BaseFragment
import com.app.presentation.contract.UserContract
import com.app.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.app.appgithubusers.extension.parser

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserBinding
        get() = FragmentUserBinding::inflate

    private val viewModel: UserViewModel by viewModels()

    private val args: UserFragmentArgs by navArgs()

    override fun prepareView(savedInstanceState: Bundle?) {
        if (viewModel.currentState.userState is UserContract.UserState.Idle)
            viewModel.setEvent(UserContract.Event.OnFetchUser(args.name))

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.userState) {
                        is UserContract.UserState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is UserContract.UserState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is UserContract.UserState.Success -> {
                            with(binding){
                                loadingPb.isVisible = false
                                txtEmail.text = state.user.email
                                txtName.text = state.user.name
                                txtOrganization.text = state.user.company
                                txtFollowers.text = state.user.followers.toString()
                                txtAccountCreationDate.text = state.user.created_at?.parser()?.formater()
                                glideSetImage(requireContext(), state.user.avatar_url, binding.imgAvatar)
                            }
                        }
                    }
                }
            }
        }
    }
}