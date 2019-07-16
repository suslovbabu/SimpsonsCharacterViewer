package com.sample.simpsonsviewer.presenters

import com.sample.simpsonsviewer.contracts.CharacterDetailContract
import com.sample.simpsonsviewer.models.RelatedTopic

class CharacterDetailPresenter(private val view: CharacterDetailContract.View, private val character: RelatedTopic): CharacterDetailContract.Presenter {
    override fun onViewCreated() {
        view.setTitle(character.getCharacterName())
        view.setDescription(character.result ?: "")
        val url = character.icon?.url
        if (!url.isNullOrBlank()) {
            view.setImageUrl(url)
        } else {
            view.setPlaceholder()
        }
    }
}