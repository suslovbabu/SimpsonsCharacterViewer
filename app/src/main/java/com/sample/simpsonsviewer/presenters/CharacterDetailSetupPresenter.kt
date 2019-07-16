package com.sample.simpsonsviewer.presenters

import com.sample.simpsonsviewer.contracts.CharacterDetailSetupContract

class CharacterDetailSetupPresenter(private val view: CharacterDetailSetupContract.View): CharacterDetailSetupContract.Presenter {
    override fun onViewCreated() {
        view.setupView()
    }

    override fun onSavedInstanceNotAvailable() {
        view.loadFragment()
    }
}