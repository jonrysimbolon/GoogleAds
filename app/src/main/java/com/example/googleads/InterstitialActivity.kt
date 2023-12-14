package com.example.googleads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.googleads.databinding.ActivityInterstitialBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterstitialBinding
    private var ctx: AppCompatActivity = this@InterstitialActivity
    private var mInterstitialAd: InterstitialAd? = null
    private val adRequest: AdRequest by lazy {
        AdRequest.Builder().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterstitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadAd(adRequest)

        binding.showBtn.setOnClickListener {
            showAdsInterstitial()
        }
    }

    private fun loadAd(adRequest: AdRequest) {
        InterstitialAd.load(
            this,
            ContextCompat.getString(ctx, R.string.admob_unit_interstitial_id),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.e(TAG, "Error: ${adError.message}")
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.e(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }

    private fun showAdsInterstitial(){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(ctx)

            // callback check event user ke ads juga harus dibawah pengecekan null pada onstart
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    Log.e(TAG, "Ad was clicked.")
                    loadAd(adRequest)
                }

                override fun onAdDismissedFullScreenContent() {
                    Log.e(TAG, "Ad dismissed fullscreen content.")
                    mInterstitialAd = null
                    loadAd(adRequest)
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e(TAG, "Ad failed to show fullscreen content.")
                    Log.e(TAG, "Error: ${adError.message}")
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    Log.e(TAG, "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.e(TAG, "Ad showed fullscreen content.")
                }
            }
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
            loadAd(adRequest)
        }
    }

    companion object {
        private const val TAG = "InterstitialActivity"
    }
}