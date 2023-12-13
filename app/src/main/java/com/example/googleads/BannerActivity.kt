package com.example.googleads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googleads.databinding.ActivityMainBinding
import com.google.android.gms.ads.MobileAds

class BannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}
    }
}