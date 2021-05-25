package com.anurag.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.SearchView.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.anurag.weather.ViewModel.WeatherViewModel
import com.anurag.weather.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  val  weatherViewModel:WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        initListener()
        weatherViewModel.getCityData()
        weatherViewModel.getData.observe(this, Observer{response->
//            if (response.weather[0].description == "clear sky" || response.weather[0].description == "mist"){
//                Glide.with(this)
//                    .load(R.drawable.clouds)
//                    .into(binding.image)
//            }else
//                if (response.weather[0].description == "haze"  || response.weather[0].description == "overcast cloud"|| response.weather[0].description == "fog"){
//                    Glide.with(this)
//                        .load(R.drawable.haze)
//                        .into(binding.image)
//                }else
//                    if (response.weather[0].description == "rain"){
//                        Glide.with(this)
//                            .load(R.drawable.rain)
//                            .into(binding.image)
//                    }
            binding.description.text = response.weather[0].description
            binding.name.text=response.name
            binding.degree.text=response.wind.deg.toString()
            binding.speed.text=response.wind.speed.toString()
            binding.temp.text=response.main.temp.toString()
            binding.humidity.text=response.main.humidity.toString()
        })
    }

    private fun initListener()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.getSearchData(it) }
//
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

}

