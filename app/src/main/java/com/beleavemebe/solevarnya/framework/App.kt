package com.beleavemebe.solevarnya.framework

import android.app.Application
import com.beleavemebe.solevarnya.framework.di.Injector
import com.beleavemebe.solevarnya.framework.repository.InMemoryLessonDataSource
import com.beleavemebe.solevarnya.framework.repository.InMemoryStudentDataSource
import com.beleavemebe.solevarnya.framework.repository.InMemorySubjectDataSource
import com.beleavemebe.solevarnya.framework.repository.InMemoryTeacherDataSource

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDataSources()
        initInjector()
    }

    private fun initDataSources() {
        InMemoryStudentDataSource.init()
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
