package com.santos.grant.coding.simplecountriesapp.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding>(resId: Int) : Fragment(resId) {
    var _binding: ViewBinding? = null
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    abstract fun getViewBinding(view: View): B
    abstract fun initViews()
    abstract fun initData()
    abstract fun initObservers()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}