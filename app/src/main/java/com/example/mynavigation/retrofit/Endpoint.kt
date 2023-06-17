package com.example.mynavigation.retrofit

import android.widget.Toast
import com.example.movieexample.url
import com.example.mynavigation.MenuItem
import com.example.mynavigation.Restaurant
import com.example.mynavigation.entity.Movie
import com.example.mynavigation.entity.MovieResponse
import com.example.mynavigation.entity.RestaurantModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("resourceManagement/distributeur")
    suspend fun getAllMovies(): Response<MovieResponse>

    @GET("restaurants")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>

    @GET("menu/restaurant/{restaurantId}")
    suspend fun getResaturantMenu(@Path("restaurantId") restaurantId: Int): Response<List<MenuItem>>

    companion object {
        private const val BASE_URL = url
        private var endpoint: Endpoint? = null

        fun createEndpoint(): Endpoint {
            if (endpoint == null) {
                synchronized(this) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    endpoint = retrofit.create(Endpoint::class.java)
                    println(endpoint) ;
                }
            }
            return endpoint!!
        }
    }
}

