package com.example.viviantest.di

import android.content.Context
import com.example.viviantest.data.ApiInterface
import com.example.viviantest.settings.Settings
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val newTestUrl: String = "https://vivian.my-project.site/"
private const val baseUrl = newTestUrl

val localModule = module {
    single {
        androidApplication().applicationContext.getSharedPreferences(
            androidContext().packageName,
            Context.MODE_PRIVATE
        )
    }
    single { Settings(androidApplication().applicationContext) }
}
val remoteModule = module {
    single {
        GsonBuilder().setLenient().create()
    }
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory((RxJava3CallAdapterFactory.create()))
            .client(get()).build()
    }
    single { get<Retrofit>().create(ApiInterface::class.java) }
}

val adapterModule = module {

}

val viewModelModule = module {

}