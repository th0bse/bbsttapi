package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.Teacher
import com.th0bse.bbsttapi.repository.TeacherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeacherService(@Autowired val repository: TeacherRepository) :
    BaseService<Teacher>(repository) {
}