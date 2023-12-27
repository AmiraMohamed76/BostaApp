package com.amira.bostaapp.di

import android.content.Context
import androidx.room.Room
import com.amira.bostaapp.data.local.AppDatabase
import com.amira.bostaapp.data.remote.ServiceApi
import com.amira.bostaapp.data.repository.RepositoryImpl
import com.amira.bostaapp.domain.repository.Repository
import com.amira.bostaapp.domain.use_cases.remote.GetRemoteAlbumsUseCase
import com.amira.bostaapp.domain.use_cases.remote.GetRemoteUserDataUseCase
import com.amira.bostaapp.domain.use_cases.UseCases
import com.amira.bostaapp.domain.use_cases.local.GetLocalAlbumsUseCase
import com.amira.bostaapp.domain.use_cases.local.GetLocalImagesUseCase
import com.amira.bostaapp.domain.use_cases.local.GetLocalUserDataUseCase
import com.amira.bostaapp.domain.use_cases.remote.GetRemoteImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: AppDatabase, api: ServiceApi): Repository {
        return RepositoryImpl(appDao = db.appDao, api = api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            albumsRemoteUseCase = GetRemoteAlbumsUseCase(repository),
            userRemoteDataUseCase = GetRemoteUserDataUseCase(repository),
            userLocalDataUseCase = GetLocalUserDataUseCase(repository),
            albumsLocalUseCase = GetLocalAlbumsUseCase(repository),
            imagesRemoteUseCase = GetRemoteImagesUseCase(repository),
            imagesLocalUseCase = GetLocalImagesUseCase(repository),
        )
    }
    @Provides
    @Singleton
    fun providesBCCSelfServiceApi(): ServiceApi {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            ).client(providesOkHttpClient()).build().create(ServiceApi::class.java)
    }
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}