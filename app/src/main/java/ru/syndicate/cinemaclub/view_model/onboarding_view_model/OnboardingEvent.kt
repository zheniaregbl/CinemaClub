package ru.syndicate.cinemaclub.view_model.onboarding_view_model

sealed interface OnboardingEvent {

    data object FinishOnboarding: OnboardingEvent
}