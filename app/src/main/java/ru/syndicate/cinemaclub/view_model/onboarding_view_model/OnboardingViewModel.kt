package ru.syndicate.cinemaclub.view_model.onboarding_view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun onEvent(event: OnboardingEvent) {
        when (event) {

            OnboardingEvent.FinishOnboarding -> {
                sharedPreferences.edit().putBoolean("finish_onboarding", true).apply()
            }
        }
    }
}