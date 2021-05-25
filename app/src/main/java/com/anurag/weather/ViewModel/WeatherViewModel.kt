package com.anurag.weather.ViewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.weather.Model.City
import com.anurag.weather.Repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
 @Inject constructor(private val weatherRepository:WeatherRepository): ViewModel(){


    val getData:MutableLiveData<City> = MutableLiveData()

       val searchCity = ConflatedBroadcastChannel<String>()//most resent data ko bhejega ye


    fun getSearchData(city:String){
        searchCity.offer(city)
    }

    fun getCityData()
    {
        viewModelScope.launch {
             searchCity.asFlow()
            .flatMapLatest{ search->
                weatherRepository.getCityData(search)
            }.catch {e->
                     Log.d("main", "${e.message}")
                 }.collect{ city->
                     getData.value=city
                 }
                 
        }
    }

}