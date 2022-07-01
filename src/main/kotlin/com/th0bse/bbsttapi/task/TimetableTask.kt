package com.th0bse.bbsttapi.task

import com.th0bse.bbsttapi.dto.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class TimetableTask {

    private lateinit var sessionCookie: String

    fun authenticate() {
        try {
            val response = Jsoup.connect("https://bbs-betriebe.de/index.php")
                .method(Connection.Method.POST)
                .data("MAIL", "bbs2schule")
                .data("SCHUELERCODE", "schüler")
                .data("formAction", "login")
                .data("formName", "stacks_in_368_page4")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .postDataCharset("UTF-8")
                .execute()
            sessionCookie = response.cookie("PHPSESSID")!!
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun getTimetableForCourse(course: Course) {
        val response = Jsoup.connect("https://bbs-betriebe.de/page-3/index.php")
            .method(Connection.Method.GET)
            .cookie("PHPSESSID", sessionCookie)
            .data("Klasse", course.name)
            .data("Schule", "0")
            .execute()
        parseTimetables(response.parse(), course)
    }

    private fun parseTimetables(document: Document, course: Course) {

        /*
        | Table         | ID                    |
        |--             |--                     |
        | Teacher       | stacks_in_49474_page1 |
        | Subject       | stacks_in_49478_page1 |
        | Room          | stacks_in_49482_page1 |
         */
        // get individual tables
        val teacherTable = document.getElementById("stacks_in_49474_page1")!!.getElementById("editableTable")
        val subjectTable = document.getElementById("stacks_in_49478_page1")!!.getElementById("editableTable")
        val roomTable = document.getElementById("stacks_in_49482_page1")!!.getElementById("editableTable")
        // get data out of those tables
        val teacherArray = teacherTable!!.parseTimetable()
        val subjectArray = subjectTable!!.parseTimetable()
        val roomArray = roomTable!!.parseTimetable()
        val dates = teacherTable.parseDates()
        // create entities and store them somewhere
        val entries: ArrayList<TimetableEntry> = ArrayList()
        for (i in 1..7) {
            for (j in 0..7) {
                if (subjectArray[j][i] != "-") {
                    // TODO: somehow add teacher names (db table?)
                    val teacher = Teacher("Not available", teacherArray[j][i])
                    val subject = Subject(subjectArray[j][i])
                    val room = Room(roomArray[j][i])
                    val entry = TimetableEntry(
                        Hour.values()[j], Weekday.values()[i - 1], dates[i - 1],
                        Instant.now().toEpochMilli(), course, subject, teacher, room
                    )
                    // save entry to database
                    entries.add(entry)
                }
            }
        }
        println(entries)
    }

    private fun Element.parseTimetable(): Array<Array<String>> {
        val result = Array(8) { Array(8) { "" } }
        val table = this.select("table > tbody")
        val rows = table.select("tr")
        rows.forEachIndexed { ri, r ->
            r.select("td").forEachIndexed { ci, c ->
                result[ri][ci] = c.text()
            }
        }
        return result
    }

    private fun Element.parseDates(): Array<String> {
        val result = Array(7) { "" }
        val table = this.select("table > thead")
        val dateRow = table.select("tr").first()
        val dates = dateRow?.select("th")

        dates?.filter { d -> d.text() != "Std." }?.forEachIndexed { di, d ->
            result[di] = d.text().split(" ")[1]
        }

        return result
    }
}