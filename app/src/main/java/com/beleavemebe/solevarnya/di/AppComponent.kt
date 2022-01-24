package com.beleavemebe.solevarnya.di

import android.content.res.AssetManager
import com.beleavemebe.solevarnya.view.search.SearchFragment
import com.beleavemebe.solevarnya.view.song.SongFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(searchFragment: SearchFragment)
    fun inject(songFragment: SongFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance assetManager: AssetManager): AppComponent
    }
}
