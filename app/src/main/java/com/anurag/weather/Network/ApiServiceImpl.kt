package com.anurag.weather.Network

import com.anurag.weather.Model.City
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {


    suspend fun  getCityData(city:String,appId:String):City =
        apiService.getCityData(city,appId)



}