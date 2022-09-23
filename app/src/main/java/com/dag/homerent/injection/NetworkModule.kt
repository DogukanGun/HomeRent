package com.dag.homerent.injection

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dag.homerent.network.interceptors.NetworkConnectionInterceptor
import com.dag.homerent.base.BaseDialogBoxUtil
import com.dag.homerent.base.HomeRentActivityListener
import com.dag.homerent.base.HomeRentApplication
import com.dag.homerent.base.HomerentNavigator
import com.dag.homerent.network.BaseNetworkLogger
import com.dag.homerent.network.DialogBoxInterceptor
import com.dag.homerent.network.HomerentService
import com.dag.homerent.network.calladapter.NetworkResponseAdapterFactory
import com.dag.homerent.network.converters.DialogBoxFailureConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHomerentActivityListener() = HomeRentActivityListener()

    @Provides
    @Singleton
    fun provideChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(HomeRentApplication.appInstance).build()
    }

    @Provides
    @Singleton
    fun provideBaseOkHttpClient(
        cache:Cache,
        chuckerInterceptor: ChuckerInterceptor,
        baseNetworkLogger: BaseNetworkLogger,
        dialogBoxInterceptor: DialogBoxInterceptor,
    ):OkHttpClient{
        return OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(baseNetworkLogger)
            .addInterceptor(dialogBoxInterceptor)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(60000L,TimeUnit.MILLISECONDS)
            .readTimeout(60000L,TimeUnit.MILLISECONDS)
            .writeTimeout(60000L,TimeUnit.MILLISECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideHomerentService(
        retrofit: Retrofit
    ): HomerentService = retrofit.create(HomerentService::class.java)


    @Provides
    @Singleton
    fun provideRetrofit(
        baseOkHttpClient:Lazy<OkHttpClient>,
        gson: Gson
    ):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(DialogBoxFailureConverter())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(HomeRentApplication.baseUrl)
            .callFactoryDelegate(baseOkHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCache(application: Application):Cache{
        val cacheSize = 10*1024*1024*2
        return Cache(application.cacheDir,cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideBaseDialogBoxUtil(
        navigator: HomerentNavigator,
        service: HomerentService,
        cleanActivityListener: HomeRentActivityListener
    ) = BaseDialogBoxUtil(navigator,service,cleanActivityListener)

    @Provides
    @Singleton
    fun provideClNavigator(
        activityListener: HomeRentActivityListener
    ) = HomerentNavigator(activityListener)
}
private fun Retrofit.Builder.callFactoryDelegate(client: Lazy<OkHttpClient>) = callFactory(
    object : Call.Factory {
        override fun newCall(request: Request) = client.get().newCall(request)
    }
)
private fun createLegacyRetrofit(gson: Gson, client: Lazy<OkHttpClient>): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(HomeRentApplication.baseUrl)
        .callFactoryDelegate(client)
        .build()
}
