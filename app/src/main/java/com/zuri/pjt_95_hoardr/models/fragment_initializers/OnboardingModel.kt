package com.zuri.pjt_95_hoardr.models.fragment_initializers

import android.content.Context
import com.zuri.pjt_95_hoardr.R

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 9:45 AM
 */
data class OnboardingModel(
    val title: String,
    val content: String,
    val buttonText: String = "Next",
    val image: Int
)

fun loadOnboardingData(context: Context) = mutableListOf<OnboardingModel>().apply {
    context.resources.let {
        val titles = it.getStringArray(R.array.onboarding_entry)
        val contents = it.getStringArray(R.array.onboarding_entry_content)
        val buttons = it.getStringArray(R.array.onboarding_entry_button_text)
        val images = it.obtainTypedArray(R.array.onboarding_entry_image)

        for((index, title) in titles.withIndex()){
            add(
                OnboardingModel(title, contents[index], buttons[index],
                images.getResourceId(index, 0)))
        }
        images.recycle()
    }
}.toList()