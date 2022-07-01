package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.TimetableEntry
import com.th0bse.bbsttapi.repository.TimetableEntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimetableEntryService(@Autowired val repository: TimetableEntryRepository) {

    fun delete(entity: TimetableEntry) = repository.delete(entity)

    fun findAll() = repository.findAll()

    fun findById(id: Long) = repository.findById(id)
}