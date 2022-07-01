package com.th0bse.bbsttapi.repository

import com.th0bse.bbsttapi.dto.*
import org.springframework.data.jpa.repository.JpaRepository

interface BaseRepository<T : BaseModel> : JpaRepository<T, Long>

interface CourseRepository : BaseRepository<Course>

interface RoomRepository : BaseRepository<Room>

interface SubjectRepository : BaseRepository<Subject>

interface TeacherRepository : BaseRepository<Teacher>

interface RawResponseRepository : BaseRepository<RawResponse>

interface TimetableEntryRepository : JpaRepository<TimetableEntry, Long>
