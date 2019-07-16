package com.sample.simpsonsviewer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.contracts.CharacterDetailContract
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.presenters.CharacterDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a list activity
 * in two-pane mode (on tablets) or a detail activity
 * on handsets.
 */
class CharacterDetailFragment : Fragment(), CharacterDetailContract.View {

    private lateinit var presenter: CharacterDetailContract.Presenter

    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(CHARACTER_DETAILS)) {
                val character = it.getSerializable(CHARACTER_DETAILS) as RelatedTopic

                presenter = CharacterDetailPresenter(this, character)

//                activity?.toolbar_layout?.title = item?.content
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.item_detail, container, false)

        presenter.onViewCreated()

        return root
    }

    companion object {
        const val CHARACTER_DETAILS = "CHARACTER_DETAILS"
    }

    // View Contract Implementation

    override fun setTitle(title: String) {
        activity?.toolbar_layout?.title = title
    }

    override fun setDescription(description: String) {
        root.characterDescriptionTextView.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    override fun setImageUrl(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.error_place_holder)
            .into(root.characterImageView)
    }

    override fun setPlaceholder() {
        root.characterImageView.setImageResource(R.drawable.error_place_holder)
    }
}
