package com.sample.simpsonsviewer.webservice

import com.sample.simpsonsviewer.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseWebservice {

    private var retrofit: Retrofit? = null

    private val BASE_URL = BuildConfig.BASE_URL

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}