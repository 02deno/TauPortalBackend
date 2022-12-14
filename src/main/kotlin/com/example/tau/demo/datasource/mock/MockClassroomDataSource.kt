package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.ClassroomDataSource
import com.example.tau.demo.model.Classroom
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockClassroomDataSource : ClassroomDataSource {
    val classrooms = mutableListOf<Classroom>(
        Classroom(UUID.fromString("35cf9f45-2ab0-46b9-8698-30565527bcbb"),"ED-5"),
        Classroom(UUID.fromString("5a76cf92-7421-4900-bcf6-64ffc37f0fd4"),"ED-6"),
        Classroom(UUID.fromString("4b50c092-1753-428f-968c-0c18f77cd394"),"ED-7")
      
        
    )

    override fun retrieveClassrooms(): Collection<Classroom> = classrooms

    override fun retrieveClassroom(code: UUID): Classroom = classrooms.firstOrNull() {it.class_code == code}
        ?:  throw NoSuchElementException("Could not find a Classroom with id $code")

    override fun createClassroom(Classroom: Classroom): Classroom {
        if(classrooms.any {it.class_code == Classroom.class_code}) {
            throw IllegalArgumentException("Classroom with id ${Classroom.class_code} already exists")
        }
        classrooms.add(Classroom)
        return Classroom
    }

    override fun deleteClassroom(code: UUID) {
        val currentClassroom = classrooms.firstOrNull() { it.class_code == code}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Classroom with code ${code}")

        classrooms.remove(currentClassroom)
    }

    override fun updateClassroom(classroom: Classroom): Classroom {
        val currentClassroom = classrooms.firstOrNull() { it.class_code == classroom.class_code}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Classroom with code ${classroom.class_code}")

        classrooms.remove(currentClassroom)
        classrooms.add(classroom)
        return classroom
    }

}