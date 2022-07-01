package com.th0bse.bbsttapi

import com.th0bse.bbsttapi.dto.Course
import com.th0bse.bbsttapi.repository.RoomRepository
import com.th0bse.bbsttapi.task.TimetableTask
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BbsttapiApplicationTests {

    @Autowired
    val roomRepository: RoomRepository? = null

    @Test
    fun contextLoads() {
    }

    @Test
    fun dev() {
        val task = TimetableTask()
        task.authenticate()
        task.getTimetableForCourse(Course("EFI1A"))
    }

}
