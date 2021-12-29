package com.beleavemebe.solevarnya.repository

import javax.security.auth.Subject

object SubjectRepository : ItemRepository<Subject> {
    override fun fetchAll(): List<Subject> {
        return emptyList()
    }
}
