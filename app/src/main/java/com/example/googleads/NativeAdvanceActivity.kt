package com.example.googleads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.googleads.databinding.ActivityInterstitialBinding
import com.example.googleads.databinding.ActivityNativeAdvanceBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

class NativeAdvanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNativeAdvanceBinding
    private var ctx: AppCompatActivity = this@NativeAdvanceActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        binding.helloWorld.setOnClickListener {
            startActivity(intent)
        }

        val adLoader =
            AdLoader.Builder(this, ContextCompat.getString(ctx, R.string.admob_unit_native_id))
                .forNativeAd { ad: NativeAd ->
                    Log.e(TAG, "body: ${ad.body}")
                    Log.e(TAG, "price: ${ad.price}")
                    Log.e(TAG, "store: ${ad.store}")

                    /*if (isDestroyed) {
                        ad.destroy()
                        return@forNativeAd
                    }*/
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.e(TAG, "adError: ${adError.message}")
                    }
                })
                .withNativeAdOptions(
                    NativeAdOptions.Builder()
                        .build()
                )
                .build()

        //adLoader.loadAd(AdRequest.Builder().build()) // 1
        adLoader.loadAds(AdRequest.Builder().build(), 3) // 3, max 5
    }

    override fun onStart() {
        super.onStart()

    }

    companion object {
        private const val TAG = "NativeAdvanceActivity"
    }
}