package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.Room
import com.th0bse.bbsttapi.repository.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoomService(@Autowired val repository: RoomRepository) :
    BaseService<Room>(repository) {
}