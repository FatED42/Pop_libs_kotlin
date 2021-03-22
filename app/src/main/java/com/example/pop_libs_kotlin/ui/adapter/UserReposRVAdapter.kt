package com.example.pop_libs_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pop_libs_kotlin.databinding.ItemRepoBinding
import com.example.pop_libs_kotlin.mvp.presenter.list.IUserReposListPresenter
import com.example.pop_libs_kotlin.mvp.view.list.IUserReposItemView

class UserReposRVAdapter(val presenter: IUserReposListPresenter) :
    RecyclerView.Adapter<UserReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposRVAdapter.ViewHolder =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: UserReposRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        IUserReposItemView {

        override var pos = -1

        override fun setReposName(repoName: String) = with(vb) {
            vb.txtRepoName.text = repoName
        }
    }
}