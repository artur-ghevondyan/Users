package com.app.appgithubusers.ui.recyclerview

import com.app.appgithubusers.databinding.ListItemUserBinding
import com.app.presentation.model.UserUiModel
import com.bumptech.glide.Glide
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class UsersAdapter @Inject constructor() :
    PagingDataAdapter<UserUiModel, UsersAdapter.ViewHolder>(differCallback) {

    private lateinit var binding: ListItemUserBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ListItemUserBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserUiModel) = with(binding){
            txtLogin.text = user.login
            txtId.text = user.id.toString()

            Glide.with(itemView)
                .load(user.avatar_url)
                .centerCrop()
                .circleCrop()
                .into(imgAvatar)

            clUser.setOnClickListener {
                onItemClickListener?.invoke(user)
            }
        }
    }

    private var onItemClickListener: ((UserUiModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (UserUiModel) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<UserUiModel>() {
            override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}