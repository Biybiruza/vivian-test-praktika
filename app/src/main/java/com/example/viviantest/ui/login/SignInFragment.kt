package com.example.viviantest.ui.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.viviantest.R
import com.example.viviantest.core.ResourceState
import com.example.viviantest.core.extentions.onClick
import com.example.viviantest.core.extentions.visibility
import com.example.viviantest.data.PostUser
import com.example.viviantest.databinding.FragmentSignInBinding
import com.example.viviantest.settings.Settings
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val settings: Settings by inject()
    private val viewModel: SignInViewModel by viewModel()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignInBinding
    private var username = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navController = Navigation.findNavController(view)
        if (!settings.isFirstLaunched){
            when(settings.role){
                "supervisor" -> {
                    val action = SignInFragmentDirections.actionSignInFragmentToSupervisorFragment()
                    navController.navigate(action)
                }
                "assistant" -> {
                    val action = SignInFragmentDirections.actionSignInFragmentToAssistantFragment()
                    navController.navigate(action)
                }
            }
        }
        binding.apply {
            btnSingIn.onClick {
                username = phoneNumber.text.toString()
                val password = password.text.toString()
                if (username.isNotEmpty() && password.isNotEmpty()){
                    viewModel.login(PostUser(username,password))
                } else {
                    when{
                        username.isEmpty() -> phoneNumber.error = getString(R.string.enter_your_login)
                        password.isEmpty() -> phoneNumber.error = getString(R.string.enter_your_password)
                    }
                }
            }
        }
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.user.observe(viewLifecycleOwner) {
            when(it.status){
                ResourceState.LOADING -> binding.loading.visibility(true)
                ResourceState.SUCCESS -> {
                    if (it.data!!.success) {
                        settings.phoneNumber = username
                        settings.branch = it.data.payload.branchInfoSignIn.branchName!!
                        when(settings.role){
                            "supervisor" -> {
                                val action =
                                    SignInFragmentDirections.actionSignInFragmentToSupervisorFragment()
                                navController.navigate(action)
                            }
                            "assistant" -> {
                                val action =
                                    SignInFragmentDirections.actionSignInFragmentToAssistantFragment()
                                navController.navigate(action)
                            }
                            else -> {
                                Toast.makeText(requireContext(),
                                    "Вы не имеете права доступа. Для входа обращайтесь к администратуру",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else if (!it.data.success){
                        Toast.makeText(requireContext(),
                            it.message, Toast.LENGTH_SHORT).show()
                    }
                    binding.loading.visibility(false)
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message,Toast.LENGTH_LONG).show()
                    binding.loading.visibility(false)
                }
            }
        }
    }
}
