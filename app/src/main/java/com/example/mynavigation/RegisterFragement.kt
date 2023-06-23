package com.example.mynavigation

import android.content.Context
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
import com.example.mynavigation.retrofit.Endpoint
import com.example.mynavigation.retrofit.SignUpData
import com.example.mynavigation.retrofit.SignUpResponse
import com.example.mynavigation.retrofit.UserData
import kotlinx.coroutines.*

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
            val username = binding.fullnameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val phone = binding.telEditText.text.toString()

            // Create a UserData object
            val userData = UserData( username, email, password, phone)

            val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                requireActivity().runOnUiThread {
                    Toast.makeText(requireActivity(), "Une erreur s'est  ", Toast.LENGTH_SHORT).show()
                }
            }
            CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
                try {
                    val response = Endpoint.createEndpoint().signUp(userData)

                    // Handle the response
                    if (response.isSuccessful && response.body() != null) {
                        val signUpResponse = response.body()!!

                        val statusCode = signUpResponse.status
                        val message = signUpResponse.message
                        val responseData = signUpResponse.data

                        // Handle the response data as needed
                        // ...

                        // Set user information in SharedPreferences if sign-up is successful
                        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("id", responseData.id.toString())
                        editor.putString("username", responseData.username)
                        editor.putString("email", responseData.email)
                        editor.putString("phone", responseData.phone)
                        editor.apply()

                        // Show a success toast
                        Toast.makeText(requireContext(), "Sign-up successful", Toast.LENGTH_SHORT).show()

                        // Redirect to the desired fragment or perform any other actions
                        // ...
                    } else {
                        // Handle unsuccessful sign-up
                        // Show an error toast
                        Toast.makeText(requireContext(), "Sign-up failed", Toast.LENGTH_SHORT).show()

                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }


            // Navigate back to the LoginFragment
            // findNavController().navigateUp()
        }
    }

}