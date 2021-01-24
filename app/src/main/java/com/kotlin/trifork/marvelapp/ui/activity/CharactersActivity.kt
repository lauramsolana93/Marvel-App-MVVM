package com.kotlin.trifork.marvelapp.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.trifork.marvelapp.R
import com.kotlin.trifork.marvelapp.common.data.dto.CharacterDto
import com.kotlin.trifork.marvelapp.common.data.dto.CharacterWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.utils.CHARACTER_ID
import com.kotlin.trifork.marvelapp.common.utils.CONNECTION
import com.kotlin.trifork.marvelapp.common.utils.helper.subscribe
import com.kotlin.trifork.marvelapp.di.injectModule
import com.kotlin.trifork.marvelapp.ui.adapter.CharactersAdapter
import com.kotlin.trifork.marvelapp.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.activity_characters.*
import kotlinx.android.synthetic.main.loading_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : AppCompatActivity(),
    CharactersAdapter.OnItemClickListener<CharacterDto> {

    private val charactersViewModel: CharactersViewModel by viewModel()
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        supportActionBar?.title = getString(R.string.characters)
        injectModule()
        setUpRecyclerView()
        getCharacters()
        setUpObservers()
    }


    private fun setUpObservers() {
        charactersViewModel.characters.subscribe(this, this::fillAdapter)
        charactersViewModel.error.subscribe(this, this::showError)
    }

    private fun setUpRecyclerView() {
        charactersRv.setHasFixedSize(true)
        charactersRv.layoutManager = LinearLayoutManager(this)
    }

    private fun getCharacters() {
        showLoading()
        charactersViewModel.getCharacters()
    }

    private fun fillAdapter(characterWrapper: CharacterWrapper) {
        if(characterWrapper != null){
            hideLoading()
            charactersRv.visibility = VISIBLE
            emptyCharacters.visibility = GONE
            charactersAdapter = CharactersAdapter(characterWrapper.data?.results ?: emptyList(), this, this)
            charactersRv.adapter = charactersAdapter
        } else {
            emptyCharacters.visibility = VISIBLE
            charactersRv.visibility = GONE
            emptyCharacters.text = getString(R.string.no_data)
        }

    }


    override fun onCharacterClicked(item: CharacterDto) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CHARACTER_ID, item.id)
        intent.putExtra(CONNECTION, true)
        startActivity(intent)
    }

    private fun showLoading(){
        progressLoading.playAnimation()
        progressLoading.visibility = VISIBLE
    }

    private fun hideLoading() {
        progressLoading.cancelAnimation()
        progressLoading.visibility = View.GONE
    }

    private fun showError(error: ErrorDto){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.error_title))
        builder.setMessage(getString(R.string.try_again))
        builder.setPositiveButton(getString(R.string.cancel_button)
        ) { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.try_again_button)
        ) { _, _ ->
            getCharacters()
        }
        builder.create()
        builder.show()
    }
}