package com.example.mynavigation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynavigation.databinding.RestaurantLayoutBinding

public class RestaurantAdapter(val data:List<Restaurant>, val ctxt: Context):RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurantLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {

            textView.text = data[position].name
            textView3.text = data[position].CuisineType
            imageView6.setImageResource(data[position].logo)
        }

        holder.binding.imageView7.setOnClickListener(){
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].fcb))
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].fcbWeb))
                ctxt.startActivity((intent))
            }
        }
        holder.binding.imageView9.setOnClickListener(){
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].instagramWeb))
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].instagramWeb))
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
        holder.binding.imageView4.setOnClickListener(){
            try {
                val data = Uri.parse("${data[position].location}")
                val intent = Intent(Intent.ACTION_VIEW, data)
                ctxt.startActivity(intent)
            }
            catch (e: Exception){
                print(e.message)
            }
        }
        /*holder.itemView.setOnClickListener {
            val intent= Intent(ctxt,MainActivity2::class.java)
            intent.putExtra("restaurant","${data[position].name}")
            ctxt.startActivity(intent)
        }*/


    }


    class MyViewHolder(val binding: RestaurantLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
