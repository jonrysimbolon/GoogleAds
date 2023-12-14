package com.example.googleads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.googleads.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class BannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var countClick: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adRequest = AdRequest.Builder().build()
        binding.apply {

            adView.loadAd(adRequest)

            adView.adListener = object : AdListener() {
                override fun onAdClicked() {
                    Log.e(TAG, "onAdClicked")
                    countClick += 1
                    binding.helloWorld.text = countClick.toString()
                }

                override fun onAdClosed() {
                    Log.e(TAG, "onAdClosed")
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, "onAdFailedToLoad: ${adError.message}")
                }

                override fun onAdImpression() {
                    Log.e(TAG, "onAdImpression")
                }

                override fun onAdLoaded() {
                    Log.e(TAG, "onAdLoaded")
                }

                override fun onAdOpened() {
                    Log.e(TAG, "onAdOpened") // url dari si iklan terbuka (pastinya setelah di klik)
                }
            }
        }
    }

    companion object{
        private const val TAG = "BannerActivity"
    }
}