package com.th0bse.bbsttapi.dto

import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
abstract class BaseModel(
    @GeneratedValue @Id var id: Long?,
) {
    constructor() : this(0)
}

@Entity
class Course(
    var name: String,
    id: Long? = 0
) : BaseModel(id)

@Entity
class Room(
    var name: String,
    id: Long? = 0
) : BaseModel(id)

@Entity
class Subject(
    var name: String,
    id: Long? = 0
) : BaseModel(id)

@Entity
class Teacher(
    var name: String,
    var shorthand: String,
    id: Long? = 0
) : BaseModel(id)

@Entity
@IdClass(TimetableCompositeKey::class)
class TimetableEntry(
    @Id var hour: Hour,
    @Id var weekday: Weekday,
    @Id var date: String,
    var epoch: Long,
    @ManyToOne var course: Course,
    @ManyToOne var subject: Subject,
    @ManyToOne var teacher: Teacher,
    @ManyToOne var room: Room,
)

class TimetableCompositeKey(
    var hour: Hour,
    var weekday: Weekday,
    var date: String
) : Serializable

@Entity
class RawResponse(
    @Lob var response: String,
    var epoch: Long,
    id: Long? = 0
) : BaseModel(id)