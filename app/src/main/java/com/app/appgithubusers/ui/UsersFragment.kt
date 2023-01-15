package com.app.appgithubusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.appgithubusers.R
import com.app.appgithubusers.databinding.FragmentUsersBinding
import com.app.appgithubusers.extension.isNetworkAvailable
import com.app.appgithubusers.ui.recyclerview.LoadMoreAdapter
import com.app.appgithubusers.ui.recyclerview.UsersAdapter
import com.app.base.BaseFragment
import com.app.presentation.contract.UsersContract
import com.app.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CHECK_REPOSITORY_COUNT = 3

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUsersBinding
        get() = FragmentUsersBinding::inflate

    private val viewModel: UsersViewModel by viewModels()

    @Inject
    lateinit var usersAdapter: UsersAdapter

    private var userLogin = ""

    override fun prepareView(savedInstanceState: Bundle?) {
        if (!requireContext().isNetworkAvailable()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.check_network_msg),
                Toast.LENGTH_LONG
            ).show()
        }
        initObservers()
        setAdapter()
    }

    private fun setAdapter() {
        usersAdapter.setOnItemClickListener { user ->
            user.login?.let {
                userLogin = it
                viewModel.setEvent(UsersContract.Event.GetUserRepoCount(userLogin))
            }
        }

        usersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                binding.loadingPb.isVisible = true
            } else {
                binding.loadingPb.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(
                        requireContext(),
                        errorState.error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        binding.usersRecyclerView.adapter = usersAdapter.withLoadStateFooter(
            LoadMoreAdapter {
                usersAdapter.retry()
            }
        )
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect {
                    usersAdapter.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiSharedState.collect {
                    if (it is UsersContract.SharedState){
                        when (val event = it.usersRepoCount) {
                            is UsersContract.UserRepoCountState.Idle -> {
                                binding.loadingPb.isVisible = false
                            }
                            is UsersContract.UserRepoCountState.Loading -> {
                                binding.loadingPb.isVisible = true
                            }
                            is UsersContract.UserRepoCountState.Success -> {
                                if (event.count > CHECK_REPOSITORY_COUNT) {
                                    findNavController().navigate(
                                        UsersFragmentDirections.actionUsersFragmentToUserFragment(
                                            userLogin
                                        )
                                    )
                                } else {
                                    findNavController().navigate(
                                        UsersFragmentDirections.actionUsersFragmentToUserFragmentWithLessThreeRepo(
                                            userLogin
                                        )
                                    )
                                }
                                binding.loadingPb.isVisible = false
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is UsersContract.Effect.ShowError -> {
                            Toast.makeText(
                                requireContext(),
                                it.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}