package com.example.pop_libs_kotlin.mvp.presenter.list

interface IListPresenter<V> {
    var itemClickListener : ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}