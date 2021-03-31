package com.example.pop_libs_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pop_libs_kotlin.databinding.ItemUserBinding
import com.example.pop_libs_kotlin.mvp.model.image.IImageLoader
import com.example.pop_libs_kotlin.mvp.presenter.list.IUsersListPresenter
import com.example.pop_libs_kotlin.mvp.view.list.IUserItemView

class UsersRVAdapter(private val presenter: IUsersListPresenter, val imageLoader: IImageLoader<ImageView>): RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemUserBinding): RecyclerView.ViewHolder(vb.root), IUserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) = with(vb){
            imageLoader.load(url, ivAvatar)
        }
    }
}