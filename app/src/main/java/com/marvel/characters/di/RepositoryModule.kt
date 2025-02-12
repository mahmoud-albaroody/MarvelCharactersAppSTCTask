package com.marvel.characters.di

import com.marvel.characters.data.remote.ApiService
import com.marvel.characters.data.repository.CharactersRepository
import com.marvel.characters.domain.CharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: ApiService,
    ): CharactersRepository {
        return CharactersRepository(
            apiService
        )
    }
    @Singleton
    @Provides
    fun provideMovieUseCase(
        apiService: CharactersRepository,
    ): CharactersUseCase {
        return CharactersUseCase(
            apiService
        )
    }
}