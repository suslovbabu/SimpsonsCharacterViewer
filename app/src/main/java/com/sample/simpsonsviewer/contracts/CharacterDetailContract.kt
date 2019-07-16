package com.sample.simpsonsviewer.contracts

interface CharacterDetailContract {

    interface View {
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setMovementMethodForDescription()
        fun setImageUrl(url: String)
        fun setPlaceholder()
    }

    interface Presenter {
        fun onViewCreated()
    }
}