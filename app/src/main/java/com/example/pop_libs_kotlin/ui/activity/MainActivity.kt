package com.example.pop_libs_kotlin.ui.activity

import android.os.Bundle
import com.example.pop_libs_kotlin.R
import com.example.pop_libs_kotlin.databinding.ActivityMainBinding
import com.example.pop_libs_kotlin.mvp.presenter.MainPresenter
import com.example.pop_libs_kotlin.mvp.view.MainView
import com.example.pop_libs_kotlin.ui.App
import com.example.pop_libs_kotlin.ui.BackClickListener
import com.example.pop_libs_kotlin.ui.adapter.UsersRVAdapter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    private val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackClickListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }

}