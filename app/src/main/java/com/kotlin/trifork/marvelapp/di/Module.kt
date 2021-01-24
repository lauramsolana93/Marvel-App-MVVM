package com.kotlin.trifork.marvelapp.di

import androidx.room.Room
import com.kotlin.trifork.marvelapp.common.data.local.DataBase
import com.kotlin.trifork.marvelapp.common.data.remote.provider.ServiceProvider
import com.kotlin.trifork.marvelapp.common.utils.network.headersInterceptor
import com.kotlin.trifork.marvelapp.common.utils.network.queryInterceptor
import com.kotlin.trifork.marvelapp.model.character.CharactersDataContract
import com.kotlin.trifork.marvelapp.model.character.CharactersRemote
import com.kotlin.trifork.marvelapp.model.character.CharactersRepository
import com.kotlin.trifork.marvelapp.model.comics.ComicLocal
import com.kotlin.trifork.marvelapp.model.comics.ComicsDataContract
import com.kotlin.trifork.marvelapp.model.comics.ComicsRemote
import com.kotlin.trifork.marvelapp.model.comics.ComicsRepository
import com.kotlin.trifork.marvelapp.model.info.InfoDataContract
import com.kotlin.trifork.marvelapp.model.info.InfoLocal
import com.kotlin.trifork.marvelapp.model.info.InfoRemote
import com.kotlin.trifork.marvelapp.model.info.InfoRepository
import com.kotlin.trifork.marvelapp.model.series.SeriesDataContract
import com.kotlin.trifork.marvelapp.model.series.SeriesLocal
import com.kotlin.trifork.marvelapp.model.series.SeriesRemote
import com.kotlin.trifork.marvelapp.model.series.SeriesRepository
import com.kotlin.trifork.marvelapp.model.splash.SplashDataContract
import com.kotlin.trifork.marvelapp.model.splash.SplashRepository
import com.kotlin.trifork.marvelapp.viewmodel.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun injectModule() = loadModule

private val loadModule by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            repositoryModule,
            database
        )
    )
}

private val serviceProvider = initRetrofit()

val viewModelModule: Module = module {
    viewModel {
        SplashViewModel(repository = get())
    }
    viewModel {
        CharactersViewModel(repository = get())
    }
    viewModel {
        ComicsViewModel(repository = get())
    }
    viewModel {
        SeriesViewModel(repository = get())
    }

    viewModel {
        InfoViewModel(repository = get())
    }
}

val repositoryModule: Module = module {
    single<SplashDataContract.Repository> {
        SplashRepository()
    }
    single<CharactersDataContract.Remote> {
        CharactersRemote(serviceProvider = serviceProvider)
    }
    single<CharactersDataContract.Repository> {
        CharactersRepository(remote = get())
    }

    single<ComicsDataContract.Repository> {
        ComicsRepository(remote = get(), local = get())
    }

    single<ComicsDataContract.Remote> {
        ComicsRemote(serviceProvider = serviceProvider)
    }

    single<ComicsDataContract.Local> {
        ComicLocal(database = get())
    }

    single<SeriesDataContract.Repository> {
        SeriesRepository(remote = get(), local = get())
    }

    single<SeriesDataContract.Local> {
        SeriesLocal(dataBase = get())
    }

    single<SeriesDataContract.Remote> {
        SeriesRemote(serviceProvider = serviceProvider)
    }

    single<InfoDataContract.Repository> {
        InfoRepository(remote = get(), local = get())
    }

    single<InfoDataContract.Remote> {
        InfoRemote(serviceProvider = serviceProvider)
    }

    single<InfoDataContract.Local> {
        InfoLocal(dataBase = get())
    }
}

val database: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            DataBase::class.java,
            "favourite.db")
            .build()
    }
}

fun initRetrofit(): ServiceProvider {

    val baseUrl = "https://gateway.marvel.com:443/v1/public/"

    val httpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .addInterceptor(queryInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(ServiceProvider::class.java)

}