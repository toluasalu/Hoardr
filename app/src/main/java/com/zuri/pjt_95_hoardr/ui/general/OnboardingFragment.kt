package com.zuri.pjt_95_hoardr.ui.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentOnboardingBinding
import com.zuri.pjt_95_hoardr.models.OnboardingModel
import com.zuri.pjt_95_hoardr.models.loadOnboardingData
import com.zuri.pjt_95_hoardr.utils.forEach

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 11:26 AM
 */
class OnboardingFragment: Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private var currentScreen = 0
    private var lastScreen = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container,  false)
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay(){
        val screens = loadOnboardingData(requireContext())
        binding.buttonOnboardingAction.setOnClickListener {
            if(lastScreen){
                findNavController().navigate(R.id.action_onboardingFragment_to_login_navigation)
            }else{
                lastScreen = currentScreen >= screens.size - 1
                displayScreen(screens[currentScreen])
            }
        }
        binding.groupOnboardingSkip.forEach{
            it.setOnClickListener {
                lastScreen = true
                displayScreen(screens.last())
            }
        }
        displayScreen(screens[currentScreen])
    }

    private fun displayScreen(screen: OnboardingModel) = with(binding){
        ++currentScreen
        screen.let {
            imageOnboarding.setImageResource(it.image)
            textOnboardingTitle.text = it.title
            textOnboardingContent.text = it.content
            buttonOnboardingAction.text = it.buttonText

            if(lastScreen) groupOnboardingSkip.visibility = View.GONE
        }
    }
}