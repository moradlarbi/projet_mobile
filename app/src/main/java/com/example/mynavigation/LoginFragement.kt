package com.example.mynavigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.mynavigation.databinding.FragmentLoginFragementBinding
import kotlinx.coroutines.runBlocking


class LoginFragement : Fragment() {
    private lateinit var binding: FragmentLoginFragementBinding
    private lateinit var userDao: UserDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginFragementBinding.inflate(inflater, container, false)
        userDao = AppDatabase.getDatabase(requireContext()).userDao()

        binding.loginButton.setOnClickListener {
            /*val username = binding.usernameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val user = runBlocking { userDao.getUser(username, password) }

                if (user != null) {
                    // Successful login

                    val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        user.id?.let { it1 -> putInt("userId", it1) }
                        putString("username", user.username)
                        apply()
                    }

                    // Navigate to the next fragment
                    val navController = Navigation.findNavController(requireView())
                    navController.navigate(R.id.action_loginFragement_to_welcomeScreen)
                } else {
                    // Failed login
                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter username and password", Toast.LENGTH_SHORT).show()
            }*/
            findNavController().navigate(R.id.action_loginFragement_to_fragment1)
        }

        binding.registerButton.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = AppDatabase.buildDatabase(requireActivity())

    }



}