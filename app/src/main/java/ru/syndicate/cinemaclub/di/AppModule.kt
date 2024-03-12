package ru.syndicate.cinemaclub.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.syndicate.cinemaclub.core.CoreConstants
import ru.syndicate.cinemaclub.data.remote.CinemaApi
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import ru.syndicate.cinemaclub.domain.repository.CinemaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCinemaApi(): CinemaApi {
        return Retrofit.Builder()
            .baseUrl(CoreConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CinemaApi::class.java)
    }

    fun provideCinemaRepository(cinemaApi: CinemaApi): CinemaRepository {
        return CinemaRepositoryImpl(cinemaApi)
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_configuration", Context.MODE_PRIVATE)
    }
}