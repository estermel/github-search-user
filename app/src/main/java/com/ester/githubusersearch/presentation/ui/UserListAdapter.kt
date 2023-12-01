package com.ester.githubusersearch.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ester.githubusersearch.databinding.ItemUserSearchBinding
import com.ester.githubusersearch.domain.model.UserData

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var users: List<UserData>? = null
    private lateinit var listener: OnItemClickListener

    fun submitData(users: List<UserData>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSearchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        users?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = users?.size ?: 0

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(username: String)
    }

    inner class ViewHolder(private val binding: ItemUserSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserData) {
            binding.tvUsername.text = user.login
            Glide.with(binding.ivAvatar.context)
                .load(user.avatarUrl)
                .into(binding.ivAvatar)
            binding.root.setOnClickListener { listener.onItemClicked(user.login) }
        }
    }
}