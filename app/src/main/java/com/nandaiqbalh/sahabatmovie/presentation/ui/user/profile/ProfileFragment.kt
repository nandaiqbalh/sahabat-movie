package com.nandaiqbalh.sahabatmovie.presentation.ui.user.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nandaiqbalh.sahabatmovie.R
import com.nandaiqbalh.sahabatmovie.data.local.model.user.UserEntity
import com.nandaiqbalh.sahabatmovie.databinding.FragmentProfileBinding
import com.nandaiqbalh.sahabatmovie.di.UserServiceLocator
import com.nandaiqbalh.sahabatmovie.presentation.ui.about.AboutAppActivity
import com.nandaiqbalh.sahabatmovie.presentation.ui.about.AboutDevActivity
import com.nandaiqbalh.sahabatmovie.presentation.ui.user.MainActivity
import com.nandaiqbalh.sahabatmovie.util.viewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModelFactory {
        ProfileViewModel(UserServiceLocator.provideUserRepository(requireContext()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getInitialData()
        observeData()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnUpdateProfile.setOnClickListener {
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.profileFragment, false)
                .build()
            findNavController().navigate(R.id.action_profileFragment_to_updateProfileFragment, null, options)
        }
        binding.btnLogout.setOnClickListener {
            viewModel.setIfUserLogin(false)
            startActivity(Intent(requireContext(), MainActivity::class.java))
            activity?.finish()
        }

//        binding.btnAboutApp.setOnClickListener {
//            val options = NavOptions.Builder()
//                .setPopUpTo(R.id.aboutAppActivity, false)
//                .build()
//            findNavController().navigate(R.id.action_profileFragment_to_aboutAppActivity, null, options)
//        }
//
//        binding.btnAboutDev.setOnClickListener {
//            val options = NavOptions.Builder()
//                .setPopUpTo(R.id.aboutDevActivity, false)
//                .build()
//            findNavController().navigate(R.id.action_profileFragment_to_aboutDevActivity, null, options)
//        }
        binding.btnAboutApp.setOnClickListener {
            startActivity(Intent(requireContext(), AboutAppActivity::class.java))
        }
        binding.btnAboutDev.setOnClickListener {
            startActivity(Intent(requireContext(), AboutDevActivity::class.java))
        }
    }

    private fun getInitialData() {
        val userId = viewModel.getUserId()
        getUserById(userId)
    }

    private fun getUserById(userId: Long?) {
        if (userId != null) {
            viewModel.getUserById(userId)
        }
    }

    private fun observeData() {
        viewModel.userByIdResult.observe(viewLifecycleOwner) {
            bindDataToView(it)
        }
    }

    private fun bindDataToView(user: UserEntity?) {
        user?.let {
            binding.apply {
                tvUsername.text = user.username
                tvEmail.text = user.email
                tvFullName.text = user.fullName
                tvDateOfBirth.text = user.dateOfBirth
                tvAddress.text = user.address
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}