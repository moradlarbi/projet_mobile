package com.example.mynavigation.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.mynavigation.AppDatabase
import com.example.mynavigation.R
import com.example.mynavigation.entity.UserDao
import com.example.mynavigation.databinding.FragmentLoginFragementBinding
import com.example.mynavigation.retrofit.Endpoint
import com.example.mynavigation.retrofit.loginData
import kotlinx.coroutines.*


class LoginFragement : Fragment() {
    private lateinit var binding: FragmentLoginFragementBinding
    private lateinit var userDao: UserDao
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginFragementBinding.inflate(inflater, container, false)
        userDao = AppDatabase.getDatabase(requireContext()).userDao()

        binding.loginButton.setOnClickListener {
            val email = binding.fullnameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()



            // Create a UserData object
            val loginData = loginData(email, password)

            val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                requireActivity().runOnUiThread {
                    Toast.makeText(requireActivity(), "Une erreur s'est  ", Toast.LENGTH_SHORT).show()
                }
            }
            CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
                try {
                    val response = Endpoint.createEndpoint().logIn(loginData)

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
                        editor.putInt("id", responseData.id)
                        editor.putString("username", responseData.username)
                        editor.putString("email", responseData.email)
                        editor.putString("phone", responseData.phone)
                        editor.apply()

                        // Show a success toast
                        Toast.makeText(requireContext(), "Sign-up successful", Toast.LENGTH_SHORT).show()

                        val navController = Navigation.findNavController(requireView())
                        navController.navigate(R.id.action_loginFragement_to_fragment1)
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