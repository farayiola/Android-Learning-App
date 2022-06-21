package com.example.storyApp

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

class Onboarding : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setOnboardItems()
        setupIndicators()
        setCurrentIndicator(0)
    }
    private fun setOnboardItems(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                 onboardingImage =   R.drawable.roll1,
                    title = "Welcome To Android Learning App",
                    description = "let talk about android and few things related to it"

                ),
                OnboardingItem(
                    onboardingImage = R.drawable.roll2,
                    title = "Era Of Digitisation",
                    description = "do you know the world has entered an era where knowledge about tech are been required in most organization?"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.rolly,
                    title = "Develop Yourself",
                    description = "every knowledge you acquire are like a seed which needs to grow more by practicing and investing in yourself"
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
             ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
            })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewPager.currentItem+1 <onboardingItemsAdapter.itemCount){
                onboardingViewPager.currentItem += 1
            }else{
                navigateToMainActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToMainActivity()
        }
        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener{
            navigateToMainActivity()
        }
    }
    private fun navigateToMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
    private fun setupIndicators(){
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}