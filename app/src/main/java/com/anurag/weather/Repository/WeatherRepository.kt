package com.anurag.weather.Repository

import com.anurag.weather.Model.City
import com.anurag.weather.Network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun  getCityData(city:String):Flow<City> = flow {
        val response= apiServiceImpl.getCityData(city,"6359baf0a80bd3d60e79ddca7f2c4e2b")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}