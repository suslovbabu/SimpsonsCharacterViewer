package com.sample.simpsonsviewer.contracts

interface CharacterDetailSetupContract {

    interface View {
        fun setupView()

        fun loadFragment()
    }

    interface Presenter {
        fun onViewCreated()
        fun onSavedInstanceNotAvailable()

    }
}