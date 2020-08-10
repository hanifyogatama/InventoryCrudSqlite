package com.binar.inventorycrudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.binar.inventorycrudsqlite.databinding.ActivityOnboardBinding

class OnBoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {}

        supportFragmentManager.beginTransaction()
            .replace(R.id.onboardContainer,OnBoarding_one())
            .commit()
    }

    fun changeToOnBoardingTwo(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.onboardContainer,OnBoarding_two())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun changeToOnBoardingThree(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.onboardContainer,OnBoarding_three())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}