package com.th0bse.bbsttapi.service

import com.th0bse.bbsttapi.dto.RawResponse
import com.th0bse.bbsttapi.repository.RawResponseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RawResponseService(@Autowired repository: RawResponseRepository) :
    BaseService<RawResponse>(repository) {
}