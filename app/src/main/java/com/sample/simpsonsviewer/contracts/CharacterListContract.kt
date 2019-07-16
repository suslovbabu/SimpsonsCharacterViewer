package com.sample.simpsonsviewer.contracts

import com.sample.simpsonsviewer.models.RelatedTopic

interface CharacterListContract {

    interface View {

        /**
         * Whether or not the activity is in two-pane mode, i.e. running on a tablet
         * device.
         */
        var twoPane: Boolean

        fun setupView()

        fun refreshCharacters()

        fun loadDetailFragment(relatedTopic: RelatedTopic)

        fun loadDetailActivity(relatedTopic: RelatedTopic)
    }

    interface Presenter {
        fun onViewCreated()

        fun clickedCharacter(position: Int)

        fun getCharacter(position: Int): RelatedTopic?

        fun getCharacterCount(): Int

        fun filterCharacters(searchText: String?)
    }
}