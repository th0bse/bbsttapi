package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.Course
import com.th0bse.bbsttapi.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseService(@Autowired val repository: CourseRepository) :
    BaseService<Course>(repository) {

}