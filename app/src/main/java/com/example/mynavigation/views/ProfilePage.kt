package com.example.mynavigation.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mynavigation.R
import com.example.mynavigation.databinding.FragmentProfilePageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilePage.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilePage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentProfilePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isLoggedIn()) {
            navigateToLogin()
            return
        }

        // Set user information if logged in
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        binding.usernameinput.setText(sharedPreferences.getString("username", ""))
        binding.emailinput.setText(sharedPreferences.getString("email", ""))
        binding.phoneinput.setText(sharedPreferences.getString("phone", ""))


        binding.logout.setOnClickListener {
            logout()
        }
    }

    private fun isLoggedIn(): Boolean {
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val phone = sharedPreferences.getString("phone", null)

        // Check if any of the required fields is missing to determine if the user is logged in
        return !(username.isNullOrEmpty() || email.isNullOrEmpty() || phone.isNullOrEmpty())
    }

    private fun navigateToLogin() {
        // Navigate to the login fragment
        findNavController().navigate(R.id.action_profile_to_login)
    }

    private fun logout() {
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("username")
        editor.remove("email")
        editor.remove("phone")
        editor.apply()

        Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()

        // Perform any additional actions after logout
        // For example, navigate to the login fragment
        findNavController().navigate(R.id.action_profile_to_home)
    }
}