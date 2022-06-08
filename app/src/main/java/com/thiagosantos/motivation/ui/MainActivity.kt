package com.thiagosantos.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.thiagosantos.motivation.infra.MotivationConstants
import com.thiagosantos.motivation.R
import com.thiagosantos.motivation.data.Mock
import com.thiagosantos.motivation.infra.SecurityPreferences
import com.thiagosantos.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private  var categoryId = MotivationConstants.FILTER.ALL


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.all)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.all.setOnClickListener(this)
        binding.happy.setOnClickListener(this)
        binding.sunny.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
           handleNextPhrase()
        }else  if (view.id in listOf(R.id.all, R.id.happy, R.id.sunny)) {
            handleFilter(view.id)
        }

    }

    private fun handleNextPhrase(){
        val phrase = Mock().getPhrase(categoryId)
        binding.phrase.text = phrase
    }

    private fun handleFilter(id: Int){

        binding.all.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))
        binding.happy.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))
        binding.sunny.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))


        when (id) {
            R.id.all -> {
                binding.all.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.happy -> {
                binding.happy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.sunny -> {
                binding.sunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {

        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.name.text = "Olá, $name!"

    }



}