package com.sample.simpsonsviewer.webservice

import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.models.GetCharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object CharacterListingManager {

    private var allCharacters: List<RelatedTopic>? = null

    fun fetchAllCharacters(callback: ((List<RelatedTopic>?) -> Unit), fromApi: Boolean = true) {
        if (!fromApi && allCharacters != null) {
            callback(allCharacters)
        }
        val service = BaseWebservice().getRetrofitInstance()?.create(CharacterListingService::class.java)
        val call = service?.getAllCharacters()
        call?.enqueue(object : Callback<GetCharactersResponse> {
            override fun onResponse(call: Call<GetCharactersResponse>, response: Response<GetCharactersResponse>) {
                allCharacters = response.body()?.characters
                callback(allCharacters)
            }

            override fun onFailure(call: Call<GetCharactersResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun filterCharacters(searchString: String): List<RelatedTopic>? {
        return allCharacters?.filter { it.text?.contains(searchString, true) ?: false }
    }
}