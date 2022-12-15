package com.example.tau.demo.controller

import com.example.tau.demo.model.Classroom
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class ClassroomControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper:ObjectMapper
) {



    val baseUrl = "/api/classrooms"

    @Nested
    @DisplayName("GET /api/classrooms")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetClassrooms {
        @Test
        fun `should return all classrooms `() {

            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$[0].location") {
                        value("ED-5")
                    }
                }

        }
    }

    @Nested
    @DisplayName("GET /api/classrooms/{code}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetClassroom{
        @Test
        fun `should return the Classroom with the given code`() {
            // given
            val code = UUID.fromString("35cf9f45-2ab0-46b9-8698-30565527bcbb")

            // when/then


            mockMvc.get("$baseUrl/$code")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.location") {value("ED-5")}

                }

        }

        @Test
        fun `should return NOT FOUND if the code does not exist`() {
            // given
            val code = "38400000-8cf0-11bd-b23e-10b96e4ef00d"

            // when / then
            mockMvc.get("$baseUrl/$code").andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }
    
    @Nested
    @DisplayName("POST /api/classrooms")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewClassroom {
        @Test
        fun `should add the new Classroom`() {
            // given
            val newClassroom = Classroom(UUID.randomUUID(),"ED-47")
            
            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newClassroom)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newClassroom))
                    }
                }

            mockMvc.get("$baseUrl/${newClassroom.class_code}")
                .andExpect { content { json(objectMapper.writeValueAsString(newClassroom)) } }

            
        }

        @Test
        fun `should return BAD REQUEST if Classroom with given code already exists`() {
            // given
            val invalidClassroom = Classroom(UUID.fromString("35cf9f45-2ab0-46b9-8698-30565527bcbb"),"ED-509")


            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidClassroom)
            }


            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }


    }


    @Nested
    @DisplayName("DELETE /api/classrooms/{code}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingClassroom{
        @Test
        @DirtiesContext
        fun `should delete the Classroom with the given code`() {
            // given
            val code = "35cf9f45-2ab0-46b9-8698-30565527bcbb"

            // when/then
            mockMvc.delete("$baseUrl/$code")
                .andDo { print() }
                .andExpect { status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$code")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return NOT FOUND if no Classroom with given code exists`() {
            // given
            val invalidcode = "38400000-8cf0-11bd-b23e-10b96e4ef00d"

            // when/then
            mockMvc.delete("$baseUrl/$invalidcode")
                .andDo { print() }
                .andExpect { status{ isNotFound() } }

        }
    }


    @Nested
    @DisplayName("PATCH /api/classrooms")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingClassroom{
        @Test
        fun `should update an existing Classroom`() {
            // given
            val updatedClassroom = Classroom(UUID.fromString("35cf9f45-2ab0-46b9-8698-30565527bcbb"),"ED-700")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedClassroom)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedClassroom))
                    }
                }

            // Classroom informations really updated,not tricked us by just returning our original object
            mockMvc.get("$baseUrl/${updatedClassroom.class_code}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedClassroom)) } }

        }

        @Test
        fun `should return BAD REQUEST if no Classroom with given code exists`() {
            // given
            val invalidClassroom = Classroom(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),"ED-700")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidClassroom)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            //we except content to be empty too

        }
    }

}


