package com.pogerapp.users_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pogerapp.core.entity.user.User
import com.pogerapp.users_list.databinding.UserItemBinding

class UsersListAdapter(
    val onClick: (Int)->Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val users = ArrayList<User>()

    fun setUsers(users: Iterable<User>){
        this.users.clear()
        this.users.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding).apply {
            itemView.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION){
                    onClick(users[adapterPosition].uid)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class UserViewHolder(
        private val usersListBinding: UserItemBinding
    ): RecyclerView.ViewHolder(usersListBinding.root){
        @SuppressLint("SetTextI18n")
        fun bind(user: User){
            usersListBinding.userName.text = user.firstName + " " + user.lastName
            Glide.with(usersListBinding.root.context)
                .load(user.avatar)
                .into(usersListBinding.userAvatar)
        }
    }
}