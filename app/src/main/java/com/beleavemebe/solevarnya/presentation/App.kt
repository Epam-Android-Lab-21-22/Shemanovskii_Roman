package com.beleavemebe.solevarnya.presentation

import android.app.Application
import com.beleavemebe.solevarnya.presentation.di.Injector
import com.beleavemebe.solevarnya.presentation.repository.InMemoryLessonDataSource
import com.beleavemebe.solevarnya.presentation.repository.InMemoryStudentDataSource
import com.beleavemebe.solevarnya.presentation.repository.InMemorySubjectDataSource
import com.beleavemebe.solevarnya.presentation.repository.InMemoryTeacherDataSource

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDataSources()
        initInjector()
    }

    private fun initDataSources() {
        InMemoryStudentDataSource.init(this)
        InMemoryTeacherDataSource.init()
        InMemorySubjectDataSource.init(this)
        InMemoryLessonDataSource.init(InMemorySubjectDataSource, InMemoryTeacherDataSource)
    }

    private fun initInjector() {
        Injector.init(
            InMemoryLessonDataSource,
            InMemoryTeacherDataSource,
            InMemorySubjectDataSource,
            InMemoryStudentDataSource
        )
    }
}
