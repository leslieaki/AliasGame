package com.example.alias.presentation

import CloudDataSource
import android.app.Application
import com.example.alias.data.*
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AliasApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://riddles-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val manageResources = ManageResources.Base(this)
        viewModel = MainViewModel(
            BaseRepository(
                CloudDataSource.Base(
                    retrofit.create(RiddleService::class.java),
                    manageResources
                ),
                CacheDataSource.Base(object : ProvideRealm {
                    override fun provideRealm(): Realm = Realm.getDefaultInstance()
                })
            )
        )
    }
}