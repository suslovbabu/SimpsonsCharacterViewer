package com.sample.simpsonsviewer.presenters

import com.sample.simpsonsviewer.contracts.CharacterListContract
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.webservice.CharacterListingManager

class CharacterListPresenter(private val view: CharacterListContract.View): CharacterListContract.Presenter {

    private var characters: List<RelatedTopic>? = null

    override fun onViewCreated() {
        view.setupView()
        makeFetchAllCharactersApiCall()
    }

    override fun filterCharacters(searchText: String?) {
        searchText?.let {
            characters = CharacterListingManager.filterCharacters(searchText)
            view.refreshCharacters()
        } ?: run {
            CharacterListingManager.fetchAllCharacters(this::fetchedAllCharacters, false)
        }
    }

    override fun clickedCharacter(position: Int) {
        characters?.getOrNull(position)?.let {
            if (view.twoPane) {
                view.loadDetailFragment(it)
            } else {
                view.loadDetailActivity(it)
            }
        }
    }

    override fun getCharacter(position: Int): RelatedTopic? {
        return characters?.get(position)
    }

    override fun getCharacterCount(): Int {
        return characters?.size ?: 0
    }

    private fun makeFetchAllCharactersApiCall() {
        CharacterListingManager.fetchAllCharacters(this::fetchedAllCharacters, true)
    }

    private fun fetchedAllCharacters(allCharacters: List<RelatedTopic>?) {
        this.characters = allCharacters
        view.refreshCharacters()

    }
}