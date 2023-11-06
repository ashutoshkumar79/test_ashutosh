package com.testdemo.view.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import com.testdemo.R
import com.testdemo.data.model.UserData
import com.testdemo.databinding.FragmentLoginBinding
import com.testdemo.utils.InputValidationUtils
import com.testdemo.utils.InputValidationUtils.validLoginInput
import com.testdemo.utils.isInternetAvailable
import com.testdemo.utils.showToast
import com.testdemo.view.base.BaseFragment
import com.testdemo.viewmodel.login.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private val mViewModel: LoginViewModel by viewModels()
    private var token: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControls()
        setupObservers()
    }

    private fun initControls() {
        mBinding.buttonLogin.setOnClickListener {
            if (validateInputs()) {
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                loginRequest()
            }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            mViewModel.mSuccess.collect {
                if (it != null) {
                    val data: UserData? = it.userData
                    if (data != null) {
                        val bundle = bundleOf("is_login" to true)
//                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
//                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
            }
        }

        lifecycleScope.launch {
            mViewModel.mErrorMessage.collect {
                showToast(requireContext(), it.toString())
            }
        }
    }

    private fun loginRequest() {
        if (isInternetAvailable(requireContext())) {
            val jsonObject = JsonObject()
            jsonObject.addProperty("user_name", mBinding.etUserName.text.toString().trim())
            jsonObject.addProperty("password", mBinding.etPassword.text.toString())
            mViewModel.login(jsonObject)
        } else {
            showToast(requireContext(), requireContext().getString(R.string.internet_error))
        }

    }

    fun validateInputs(): Boolean {
        return if (validLoginInput(mBinding.etUserName.text.toString().trim(), mBinding.etPassword.text.toString())) {
            showToast(requireContext(), "Username or password can't be empty.")
            false
        } else {
            true
        }
    }

}