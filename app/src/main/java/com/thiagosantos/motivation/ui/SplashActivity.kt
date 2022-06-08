package com.thiagosantos.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.thiagosantos.motivation.infra.MotivationConstants
import com.thiagosantos.motivation.R
import com.thiagosantos.motivation.infra.SecurityPreferences
import com.thiagosantos.motivation.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSplash.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUserName()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_splash) {
            handleSave()

        }
    }

    private  fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = binding.edittextSplash.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()


        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}