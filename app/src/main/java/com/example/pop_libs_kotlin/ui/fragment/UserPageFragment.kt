package com.example.pop_libs_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pop_libs_kotlin.databinding.FragmentUserPageBinding
import com.example.pop_libs_kotlin.mvp.model.api.ApiHolder
import com.example.pop_libs_kotlin.mvp.model.cache.GitHubUserReposCache
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import com.example.pop_libs_kotlin.mvp.model.image.IImageLoader
import com.example.pop_libs_kotlin.mvp.model.repo.RetrofitGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.presenter.UserPagePresenter
import com.example.pop_libs_kotlin.mvp.view.UserPageView
import com.example.pop_libs_kotlin.ui.App
import com.example.pop_libs_kotlin.ui.BackClickListener
import com.example.pop_libs_kotlin.ui.adapter.UserReposRVAdapter
import com.example.pop_libs_kotlin.ui.image.GlideImageLoader
import com.example.pop_libs_kotlin.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserPageFragment(): MvpAppCompatFragment(), UserPageView, BackClickListener {

    companion object {
        private const val USERNAME_ARG = "user"

        fun newInstance(user: GitHubUser) = UserPageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USERNAME_ARG, user)
            }
        }
    }

    private val presenter: UserPagePresenter by moxyPresenter {
        val user = arguments?.getParcelable<GitHubUser>(USERNAME_ARG) as GitHubUser
        UserPagePresenter(user).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentUserPageBinding? = null
    private var adapter: UserReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserPageBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    override fun init() {
        vb?.rvUserRepos?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserReposRVAdapter(presenter.userReposListPresenter)
        vb?.rvUserRepos?.adapter = adapter
    }

    override fun updateReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        vb?.userLogin?.text = text
    }

    override fun setImage(url: String) {
        //imageLoader.load(url, vb!!.ivAvatar)
    }

    override fun showRepoInfo(scoreFork: Int, scoreViews: Int, language: String) {
        Toast.makeText(
            context,
            "Число форков: $scoreFork, \nПросмотры: $scoreViews, \nИспользуемый язык: $language",
            Toast.LENGTH_SHORT
        ).show()
    }

}