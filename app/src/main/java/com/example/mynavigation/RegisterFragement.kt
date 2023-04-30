package com.example.mynavigation

import android.os.Bundle
import android.view.View
import com.example.mynavigation.databinding.FragementRegisterBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.mynavigation.databinding.FragmentLoginFragementBinding
import kotlinx.coroutines.runBlocking

class RegisterFragement : Fragment() {
    private lateinit var binding: FragementRegisterBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragementRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            // Get the username and password from the EditTexts
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // TODO: Add database logic to register the user

            // Navigate back to the LoginFragment
            findNavController().navigateUp()
        }
    }

}