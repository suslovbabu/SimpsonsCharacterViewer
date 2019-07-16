package com.sample.simpsonsviewer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.contracts.CharacterListContract
import com.sample.simpsonsviewer.viewholders.ICharacterViewHolder
import kotlinx.android.synthetic.main.item_list_content.view.*

class CharacterRecyclerViewAdapter(private val presenter: CharacterListContract.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = presenter.getCharacter(position)
        when (holder) {
            is CharacterViewHolder -> {
                holder.setTitle(item?.getCharacterName() ?: "")
            }
        }
        holder.itemView.setOnClickListener {
            presenter.clickedCharacter(position)
        }
    }

    override fun getItemCount() = presenter.getCharacterCount()

    inner class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view), ICharacterViewHolder {

        override fun setTitle(title: String) {
            view.characterTitle.text = title
        }
    }
}