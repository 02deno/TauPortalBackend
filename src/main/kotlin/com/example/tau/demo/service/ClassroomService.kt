package com.example.tau.demo.service

import com.example.tau.demo.datasource.ClassroomDataSource
import com.example.tau.demo.model.Classroom
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClassroomService(private val dataSource : ClassroomDataSource) {
    fun getClassrooms(): Collection<Classroom> = dataSource.retrieveClassrooms()
    fun getClassroom(code : UUID): Classroom = dataSource.retrieveClassroom(code)
    fun addClassroom(classroom: Classroom): Classroom = dataSource.createClassroom(classroom)
    fun deleteClassroom(code : UUID) = dataSource.deleteClassroom(code)
    fun updateClassroom(classroom: Classroom): Classroom = dataSource.updateClassroom(classroom)
}