package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.Subject
import com.th0bse.bbsttapi.repository.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SubjectService(@Autowired val repository: SubjectRepository) :
    BaseService<Subject> (repository) {
}