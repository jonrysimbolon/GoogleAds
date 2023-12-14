package com.example.googleads

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.googleads.databinding.ActivityMain2Binding
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private var ctx: AppCompatActivity = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        binding.apply {
            bannerBtn.setOnClickListener {
                startActivity(
                    Intent(
                        ctx, BannerActivity::class.java
                    )
                )
            }

            interstitialBtn.setOnClickListener {
                startActivity(
                    Intent(
                        ctx, InterstitialActivity::class.java
                    )
                )
            }

            rewardBtn.setOnClickListener {
                startActivity(
                    Intent(
                        ctx, RewardActivity::class.java
                    )
                )
            }

            nativeAdvBtn.setOnClickListener {
                startActivity(
                    Intent(
                        ctx, NativeAdvanceActivity::class.java
                    )
                )
            }

            rewInterstitial.setOnClickListener {
                /*startActivity(
                    Intent(
                        ctx, RewardInterstitialActivity::class.java
                    )
                )*/
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}