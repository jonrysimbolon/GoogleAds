package com.example.googleads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.googleads.databinding.ActivityRewardBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRewardBinding
    private var ctx: AppCompatActivity = this@RewardActivity
    private var rewardedAd: RewardedAd? = null
    private val adRequest: AdRequest by lazy {
        AdRequest.Builder().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showBtn.setOnClickListener {
            showAdsReward()
        }

        loadAd()
    }

    private fun loadAd() {
        RewardedAd.load(
            this,
            ContextCompat.getString(ctx, R.string.admob_unit_rewarded_id),
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.e(TAG, adError.message)
                    rewardedAd = null
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.e(TAG, "Ad was loaded.")
                    rewardedAd = ad
                }
            })
    }

    private fun showAdsReward(){
        rewardedAd?.let { ad ->
            ad.show(this) { rewardItem ->
                // Handle the reward.
                val rewardAmount = rewardItem.amount
                val rewardType = rewardItem.type
                Log.e(TAG, "User earned the reward.")
                Log.e(TAG, "reward amount: $rewardAmount")
                Log.e(TAG, "reward type: $rewardType")
            }
        } ?: run {
            Log.e(TAG, "The rewarded ad wasn't ready yet.")
            loadAd()
        }

        rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.e(TAG, "Ad was clicked.")
                loadAd()
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                rewardedAd = null
                loadAd()
            }

            override fun onAdFailedToShowFullScreenContent(addError: AdError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.")
                rewardedAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.e(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.e(TAG, "Ad showed fullscreen content.")
            }
        }
    }

    companion object {
        private const val TAG = "RewardActivity"
    }
}