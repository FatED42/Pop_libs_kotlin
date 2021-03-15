package com.example.pop_libs_kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pop_libs_kotlin.R
import com.example.pop_libs_kotlin.databinding.ActivityMainBinding
import com.example.pop_libs_kotlin.mvp.model.GitHubUsersRepo
import com.example.pop_libs_kotlin.mvp.presenter.MainPresenter
import com.example.pop_libs_kotlin.mvp.view.MainView
import com.example.pop_libs_kotlin.ui.App
import com.example.pop_libs_kotlin.ui.BackClickListener
import com.example.pop_libs_kotlin.ui.adapter.UsersRVAdapter
import com.example.pop_libs_kotlin.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
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