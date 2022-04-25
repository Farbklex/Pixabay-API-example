package de.hypar.hoffman_pixa.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.hypar.pixabay_api.PixaBayApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://pixabay.com"

    @Singleton
    @Provides
    fun provideApi(): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideImageApi(retrofit: Retrofit): PixaBayApi = retrofit.create(PixaBayApi::class.java)
}