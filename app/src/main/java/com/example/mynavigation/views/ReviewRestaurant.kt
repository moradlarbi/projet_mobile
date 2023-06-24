package com.example.mynavigation.views
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mynavigation.R
import com.example.mynavigation.models.MyModel
import com.example.mynavigation.retrofit.Endpoint
import com.example.mynavigation.retrofit.ReviewData
import kotlinx.coroutines.launch

class ReviewRestaurant : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var myModel: MyModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review_restaurant, container, false)
        val addReviewBtn: Button = view.findViewById(R.id.addReviewBtn)
        val reviewEditText: EditText = view.findViewById(R.id.localisation)

        // Get SharedPreferences instance
        sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        addReviewBtn.setOnClickListener {
            val review = reviewEditText.text.toString().trim()

            if (review.isNotEmpty()) {
                val userId = sharedPreferences.getInt("id", -1)
                myModel = ViewModelProvider(requireActivity()).get(MyModel::class.java)
                val restaurantId = myModel.data.first().id

                if (userId != -1 && restaurantId != -1) {
                    val reviewData = ReviewData(review, userId, restaurantId)

                    lifecycleScope.launch {
                        try {
                            val response = Endpoint.createEndpoint().addReview(reviewData)

                            if (response.isSuccessful && response.body() != null) {
                                // Review added successfully
                                Toast.makeText(requireContext(), "Review added successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                // Handle unsuccessful review addition
                                Toast.makeText(requireContext(), "Failed to add review", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            // Handle exception
                            Toast.makeText(requireContext(), "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Handle missing user ID or restaurant ID
                    Toast.makeText(requireContext(), "Missing user ID or restaurant ID", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Handle empty review text
                Toast.makeText(requireContext(), "Please enter a review", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
