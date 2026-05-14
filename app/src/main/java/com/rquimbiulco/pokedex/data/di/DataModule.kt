package com.rquimbiulco.pokedex.data.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rquimbiulco.pokedex.data.datasource.api.ApiService
import com.rquimbiulco.pokedex.data.datasource.database.PokeDatabase
import com.rquimbiulco.pokedex.data.datasource.database.dao.UserDao
import com.rquimbiulco.pokedex.data.repository.AuthRepositoryImpl
import com.rquimbiulco.pokedex.data.repository.PokemonRepositoryImpl
import com.rquimbiulco.pokedex.data.repository.UserRepositoryImpl
import com.rquimbiulco.pokedex.domain.repository.AuthRepository
import com.rquimbiulco.pokedex.domain.repository.PokemonRepository
import com.rquimbiulco.pokedex.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Dns
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.net.Inet4Address
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .dns { hostname ->
                // Filtra para devolver solo direcciones IPv4
                Dns.SYSTEM.lookup(hostname).filterIsInstance<Inet4Address>()
            }
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }

    @Provides
    @Singleton
    fun providesPokemonDatabase(@ApplicationContext appContext: Context): PokeDatabase {
        return Room.databaseBuilder(appContext, PokeDatabase::class.java, "PokeDatabase")
            .build()
    }

    @Provides
    fun provideAuthRepository(userRepository: UserRepository): AuthRepository {
        return AuthRepositoryImpl(userRepository)
    }

    @Provides
    fun providesUserDao(database: PokeDatabase) = database.userDao()

    @Provides
    fun providesUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    fun providesPokemonDao(database: PokeDatabase) = database.pokemonDao()

    @Provides
    fun providesPokemonRepository(
        apiService: ApiService,
        database: PokeDatabase
    ): PokemonRepository {
        return PokemonRepositoryImpl(apiService, database)
    }
}
