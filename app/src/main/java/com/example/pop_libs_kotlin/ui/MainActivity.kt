package com.example.pop_libs_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pop_libs_kotlin.databinding.ActivityMainBinding
import com.example.pop_libs_kotlin.mvp.presenter.MainPresenter
import com.example.pop_libs_kotlin.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener {presenter.counterClickOne()}
        vb?.btnCounter2?.setOnClickListener {presenter.counterClickTwo()}
        vb?.btnCounter3?.setOnClickListener {presenter.counterClickThree()}
    }

    override fun setFirstButtonText(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setSecondButtonText(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setThirdButtonText(text: String) {
        vb?.btnCounter3?.text = text
    }
}