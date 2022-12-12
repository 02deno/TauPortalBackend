package com.example.tau.demo.datasource.mock;

import com.example.tau.demo.model.Classroom;
import com.example.tau.demo.model.Lesson;
import com.example.tau.demo.model.Student;
import org.springframework.stereotype.Repository;
import com.example.tau.demo.datasource.StudentDataSource

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Repository
class MockStudentDataSource : StudentDataSource {


    val students = mutableListOf<Student>(
        Student(
            "190503029",
            "INF",
            5,
            Lesson(
                UUID.randomUUID(),
                "Yapay Zeka",
                Classroom(UUID.randomUUID(), "ED-5"),
                LocalDateTime.now(),
                "Canan Yıldız",
                "Carlsten",
                "mrb"
            )),

        Student(
            "190503031",
            "INF",
            5,
            Lesson(
                UUID.randomUUID(),
                "Tıbbı Görüntüleme",
                Classroom(UUID.randomUUID(), "ED-6"),
                LocalDateTime.of(2022, 6, 30, 12, 0,0),
                "Sercan İpek Ugay",
                "Mehmet",
                "nbr"
            )),

        Student(
            "190503019",
            "INF",
            5,
            Lesson(
                UUID.randomUUID(),
                "Veri Analizi",
                Classroom(UUID.randomUUID(), "ED-7"),
                LocalDateTime.of(2022, 3, 13, 11, 0,0),
                "Emre Işık",
                "Ali",
                "slm"
            )),


    )

    
    override fun retrieveStudents(): kotlin.collections.Collection<Student> = students

    override fun retrieveStudent(studentNumber: String): Student = students.firstOrNull() {it.studentNumber == studentNumber}
        ?:  throw NoSuchElementException("Could not find a Student with studentNumber $studentNumber")

    override fun createStudent(student: Student): Student {
        if(students.any {it.studentNumber == student.studentNumber}) {
            throw IllegalArgumentException("Student with studentNumber ${student.studentNumber} already exists")
        }
        students.add(student)
        return student
    }

    override fun deleteStudent(studentNumber: String) {
        val currentStudent = students.firstOrNull() { it.studentNumber == studentNumber}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Student with studentNumber ${studentNumber}")

        students.remove(currentStudent)
    }

    override fun updateStudent(student: Student): Student {
        val currentStudent = students.firstOrNull() { it.studentNumber == student.studentNumber}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Student with studentNumber ${student.studentNumber}")

        students.remove(currentStudent)
        students.add(student)
        return student
    }
}