package com.beleavemebe.solevarnya

import android.app.Application
import com.beleavemebe.solevarnya.repository.StudentRepository
import com.beleavemebe.solevarnya.repository.TeacherRepository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initRepositories()
    }

    private fun initRepositories() {
        StudentRepository.init(this)
        TeacherRepository.init(this)
    }
}