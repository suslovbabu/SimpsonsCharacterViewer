package com.sample.simpsonsviewer.webservice

import com.sample.simpsonsviewer.BuildConfig
import com.sample.simpsonsviewer.models.GetCharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface CharacterListingService {

    @GET
    fun getAllCharacters(@Url url: String = BuildConfig.BASE_URL): Call<GetCharactersResponse>
}