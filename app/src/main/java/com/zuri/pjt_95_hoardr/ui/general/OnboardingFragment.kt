package com.zuri.pjt_95_hoardr.ui.general

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentOnboardingBinding
import com.zuri.pjt_95_hoardr.models.fragment_initializers.OnboardingModel
import com.zuri.pjt_95_hoardr.models.fragment_initializers.loadOnboardingData
import com.zuri.pjt_95_hoardr.utils.CustomViewPager
import com.zuri.pjt_95_hoardr.utils.forEach

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 11:26 AM
 */
class OnboardingFragment: CustomViewPager<OnboardingModel>() {
    private lateinit var binding: FragmentOnboardingBinding
    override lateinit var screens: List<OnboardingModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container,  false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screens = loadOnboardingData(requireContext())
        binding.buttonOnboardingAction.setOnClickListener {
            if(isLastScreen){
                findNavController().navigate(R.id.action_onboardingFragment_to_login_navigation)
            }else{
                displayScreen(screens[currentScreen++])
            }
        }
        binding.groupOnboardingSkip.forEach{
            it.setOnClickListener {
                currentScreen = screens.size
                displayScreen(screens.last())
            }
        }
        displayScreen(screens[currentScreen++])
    }

    override fun displayScreen(screen: OnboardingModel) = with(binding){
        Log.e(TAG, "displayScreen: Current Screen $currentScreen, Is Last Screen $isLastScreen", )
        screen.let {
            imageOnboarding.setImageResource(it.image)
            textOnboardingTitle.text = it.title
            textOnboardingContent.text = it.content
            buttonOnboardingAction.text = it.buttonText

            groupOnboardingSkip.visibility = if(isLastScreen) View.GONE else View.VISIBLE
        }
    }

    companion object{
        const val TAG = "OnboardingFragment"
    }
}