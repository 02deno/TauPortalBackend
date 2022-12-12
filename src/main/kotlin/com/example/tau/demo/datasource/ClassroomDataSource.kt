package com.example.tau.demo.datasource

import com.example.tau.demo.model.Classroom
import java.time.LocalDateTime
import java.util.UUID

interface ClassroomDataSource {
    fun retrieveClassrooms() : Collection<Classroom>
    fun retrieveClassroom(code : UUID): Classroom
    fun createClassroom(classroom: Classroom): Classroom
    fun deleteClassroom(code : UUID)
    fun updateClassroom(classroom: Classroom): Classroom
}