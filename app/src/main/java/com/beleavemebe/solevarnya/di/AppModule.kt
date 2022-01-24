package com.beleavemebe.solevarnya.di

import dagger.*

@Module(includes = [DomainModule::class, DataModule::class])
class AppModule
