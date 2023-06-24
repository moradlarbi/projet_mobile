package com.example.mynavigation.retrofit

import com.example.movieexample.url
import com.example.mynavigation.entity.MenuItem
import com.example.mynavigation.models.Restaurant
import com.example.mynavigation.entity.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoint {

    @GET("resourceManagement/distributeur")
    suspend fun getAllMovies(): Response<MovieResponse>

    @GET("restaurants")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>

    @GET("menu/restaurant/{restaurantId}")
    suspend fun getResaturantMenu(@Path("restaurantId") restaurantId: Int): Response<List<MenuItem>>

    @POST("auth/signup")
    suspend fun signUp(
        @Body userData: UserData
    ): Response<SignUpResponse>

    @POST("rate")
    suspend fun rateRestaurant(
        @Body userData: rateData
    ): Response<addDataRes>

    @POST("auth/login")
    suspend fun logIn(
        @Body userData: loginData
    ): Response<SignUpResponse>

    @GET("rate/restaurant/{restaurantId}")
    suspend fun getRestaurantRating(@Path("restaurantId") restaurantId: String): Response<RestaurantRating>

    @GET("rate/rate/{userId}/{restaurantId}")
    suspend fun getUserRate(
        @Path("userId") userId: Int,
        @Path("restaurantId") restaurantId: Int
    ): Response<UserRate>

    @POST("order")
    suspend fun addOrder(
        @Body orderData: OrderData
    ): Response<addDataRes>

    @POST("review")
    suspend fun addReview(
        @Body reviewData: ReviewData
    ): Response<addDataRes>

    @GET("order/{userId}")
    suspend fun getOrders(
        @Path("userId") userId: Int
    ): Response<List<OrderResponse>>

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



data class SignUpResponse(
    val status: Int,
    val message: String,
    val data: SignUpData
)

data class UserData(

    val username: String,
    val email: String,
    val password: String,
    val phone: String
)
data class SignUpData(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val phone: String
)

data class loginData(
    val email: String,
    val password: String,
)

data class rateData(
   val rating:Int,
    val userId:Int,
val restaurantId:Int
)

data class addDataRes(
    val msg:String,
)

data class RestaurantRating(
    val averageRating:String
)

data class UserRate(
    val rating: Int
)

data class OrderData(
    val userId: Int,
    val items: List<OrderItem>,
    val location: String,
    val restaurantId: Int ,
    val note:String
)

data class OrderItem(
    val id: Int,
    val quantity: Int
)

data class OrderResponse(
    val id: Int,
    val note: String,
    val location: String,
    val createdAt: String,
    val status: String,
    val userId: Int,
    val restaurantId: Int
)

data class ReviewData(
    val review: String,
    val userId: Int,
    val restaurantId: Int
)

