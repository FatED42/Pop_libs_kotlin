package com.example.pop_libs_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pop_libs_kotlin.databinding.FragmentUsersBinding
import com.example.pop_libs_kotlin.mvp.model.api.ApiHolder
import com.example.pop_libs_kotlin.mvp.model.cache.GitHubUsersCache
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import com.example.pop_libs_kotlin.mvp.model.repo.RetrofitGitHubUsersRepo
import com.example.pop_libs_kotlin.mvp.presenter.UsersPresenter
import com.example.pop_libs_kotlin.mvp.view.UsersView
import com.example.pop_libs_kotlin.ui.App
import com.example.pop_libs_kotlin.ui.BackClickListener
import com.example.pop_libs_kotlin.ui.adapter.UsersRVAdapter
import com.example.pop_libs_kotlin.ui.image.GlideImageLoader
import com.example.pop_libs_kotlin.ui.navigation.AndroidScreens
import com.example.pop_libs_kotlin.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment: MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            RetrofitGitHubUsersRepo(ApiHolder.api, AndroidNetworkStatus(App.instance), GitHubUsersCache(Database.getInstance())),
            App.instance.router, AndroidScreens(),
            AndroidSchedulers.mainThread())
    }
    private var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false). also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClicked()

}