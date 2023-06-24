package com.example.mynavigation;
import android.content.Context
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynavigation.databinding.RestaurantLayoutBinding
import com.example.mynavigation.retrofit.Endpoint
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class RestaurantAdapter(val data:List<Restaurant>, val ctxt: Context):RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>(){
    var onItemClick: ((Restaurant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurantLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {

            textView.text = data[position].name
            textView3.text = data[position].CuisineType
            Picasso.get().load(data[position].logo).into(imageView6)
            //imageView6.setImageResource(data[position].logo)
        }

        holder.binding.imageView7.setOnClickListener(){
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].fcb))
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].fcb))
                ctxt.startActivity((intent))
            }
        }
        holder.binding.imageView9.setOnClickListener(){
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].instagram))
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].instagram))
                ctxt.startActivity((intent))
            }
        }
        holder.binding.imageView5.setOnClickListener(){
            try {
                val data = Uri.parse("tel:${data[position].phone}")
                val intent = Intent(Intent.ACTION_DIAL, data)
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                print(e.message)
            }
        }
        holder.binding.imageView8.setOnClickListener(){
            try {
                val data = Uri.parse("mailto:${data[position].email}")
                val intent = Intent(Intent.ACTION_SENDTO, data)
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                print(e.message)
            }
        }
        holder.binding.imageView4.setOnClickListener {
            try {
                val latitude = data[position].latitude
                val longitude = data[position].longitude
                val geoUri = Uri.parse("geo:$latitude,$longitude")
                val intent = Intent(Intent.ACTION_VIEW, geoUri)
                ctxt.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        holder.binding.imageContainer.setOnClickListener {
            onItemClick?.invoke(data[position])
        }

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

                Toast.makeText(ctxt, "Une erreur s'est  ", Toast.LENGTH_SHORT).show()

        }
        CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
            try {
                val response = Endpoint.createEndpoint().getRestaurantRating(data[position].id.toString())

                // Handle the response
                if (response.isSuccessful && response.body() != null) {
                    val resRating = response.body()!!
                    holder.binding.restaurantRating.text  = resRating.averageRating.toString();
                } else {
                    // Handle unsuccessful sign-up
                    // Show an error toast
                    Toast.makeText(ctxt, data[position].id.toString(), Toast.LENGTH_SHORT).show()

                }
            } catch (e: Exception) {
                Toast.makeText(ctxt, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }


    class MyViewHolder(val binding: RestaurantLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
