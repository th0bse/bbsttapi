package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.BaseModel
import com.th0bse.bbsttapi.repository.BaseRepository

abstract class BaseService<T : BaseModel>(
    protected val baseRepository: BaseRepository<T>
) {

    fun delete(entity: T) = baseRepository.delete(entity)

    fun findAll() = baseRepository.findAll()

    fun findById(id: Long) = baseRepository.findById(id)
}