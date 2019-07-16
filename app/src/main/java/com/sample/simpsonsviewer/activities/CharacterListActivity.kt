package com.sample.simpsonsviewer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.adapters.CharacterRecyclerViewAdapter
import com.sample.simpsonsviewer.contracts.CharacterListContract
import com.sample.simpsonsviewer.fragments.CharacterDetailFragment
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.presenters.CharacterListPresenter
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*


/**
 * This activity has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [CharacterDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class CharacterListActivity : AppCompatActivity(), CharacterListContract.View, SearchView.OnQueryTextListener {

    override var twoPane: Boolean = false

    private val presenter: CharacterListContract.Presenter = CharacterListPresenter(this)

    private lateinit var adapter: CharacterRecyclerViewAdapter

    // Life cycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        presenter.onViewCreated()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = CharacterRecyclerViewAdapter(presenter)
        recyclerView.adapter = adapter
    }

    // View Contract Implementation

    override fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // The detail container view will be present only in the
        // large-screen layouts (res/values-sw600dp).
        // If this view is present, then the
        // activity should be in two-pane mode.
        twoPane = (characterDetailContainer != null)

        searchView.setOnQueryTextListener(this)

        setupRecyclerView(charactersRecyclerView)
    }

    override fun refreshCharacters() {
        adapter.notifyDataSetChanged()
    }

    override fun loadDetailFragment(relatedTopic: RelatedTopic) {
        val fragment = CharacterDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CharacterDetailFragment.CHARACTER_DETAILS, relatedTopic)
            }
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.characterDetailContainer, fragment)
            .commit()
    }

    override fun loadDetailActivity(relatedTopic: RelatedTopic) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(CharacterDetailFragment.CHARACTER_DETAILS, relatedTopic)
        }
        startActivity(intent)
    }

    // SearchView OnQueryTextListener Implementation

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.filterCharacters(newText)
        return true
    }
}
