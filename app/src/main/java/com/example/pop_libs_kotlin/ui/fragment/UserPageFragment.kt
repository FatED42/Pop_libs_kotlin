package com.example.pop_libs_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pop_libs_kotlin.databinding.FragmentUserPageBinding
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.presenter.UserPagePresenter
import com.example.pop_libs_kotlin.mvp.view.UserPageView
import com.example.pop_libs_kotlin.ui.App
import com.example.pop_libs_kotlin.ui.BackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserPageFragment: MvpAppCompatFragment(), UserPageView, BackClickListener {

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
        UserPagePresenter(user, App.instance.router)
    }

    private var vb: FragmentUserPageBinding? = null

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

    override fun backPressed() = presenter.backClicked()

    override fun setLogin(text: String) {
        vb?.userNameTV?.text = text
    }

}