package com.anurag.weather.Model

data class City(
    val weather: ArrayList<Weather>,
    val main:Main,
    val wind:Wind,
    val name:String
)


class Wind (
    val speed:Float,
    val deg: Float

    ) {

}


class Main(
    val temp:Double,
    val humidity:Int

)




class Weather(
    val description:String

)
