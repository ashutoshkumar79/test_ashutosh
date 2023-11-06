package com.testdemo.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.testdemo.data.model.UserData
import com.testdemo.databinding.FragmentHomeBinding
import com.testdemo.databinding.FragmentLoginBinding
import com.testdemo.utils.showToast
import com.testdemo.view.base.BaseFragment
import com.testdemo.viewmodel.home.HomeViewModel
import com.testdemo.viewmodel.login.LoginViewModel
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private val mViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControls()
        setupObservers()
    }

    private fun initControls() {

    }

    private fun setupObservers() {
        lifecycleScope.launch {
            mViewModel.mSuccess.collect{
                if(it!=null) {
                    val data: UserData? = it.userData
                    if(data!=null) {

                    }
                }
            }
        }

        lifecycleScope.launch {
            mViewModel.mErrorMessage.collect{
                showToast(requireContext(), it.toString())
            }
        }
    }




}