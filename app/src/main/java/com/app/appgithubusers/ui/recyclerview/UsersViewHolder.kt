package com.app.appgithubusers.ui.recyclerview

import android.widget.ImageView
import android.widget.TextView
import com.app.appgithubusers.R
import com.app.appgithubusers.databinding.ListItemUserBinding
import com.app.base.BaseViewHolder
import com.app.presentation.model.UserUiModel
import com.bumptech.glide.Glide

class UsersViewHolder(
    private val binding : ListItemUserBinding,
    private val click : ((UserUiModel?) -> Unit)? = null
) : BaseViewHolder<UserUiModel, ListItemUserBinding>(binding) {


    init {
        binding.root.setOnClickListener {
            click?.invoke(getRowItem())
        }
    }

    override fun bind() {
        getRowItem()?.let {
            val txtName = itemView.findViewById<TextView>(R.id.txtLogin)
            val txtId = itemView.findViewById<TextView>(R.id.txtId)
            val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)

            txtName.text = it.login
            txtId.text = it.id.toString()

            Glide.with(itemView)
                .load(it.avatar_url)
                .centerCrop()
                .circleCrop()
                .into(imgAvatar)
        }
    }
}