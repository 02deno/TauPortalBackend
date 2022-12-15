package com.example.tau.demo.controller

import com.example.tau.demo.model.Classroom
import com.example.tau.demo.model.Lesson
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
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
internal class LessonControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper:ObjectMapper
) {



    val baseUrl = "/api/lessons"

    @Nested
    @DisplayName("GET /api/lessons")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetLessons {
        @Test
        fun `should return all lessons `() {

            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$[0].name") {
                        value("Tıbbı Görüntüleme")
                    }
                }

        }
    }

    @Nested
    @DisplayName("GET /api/lessons/{name}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetLesson{
        @Test
        fun `should return the Lesson with the given name`() {
            // given
            val name = "Yapay Zeka"

            // when/then
            mockMvc.get("$baseUrl/$name")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.prof") {value("Canan Yıldız")}
                    
                }

        }

        @Test
        fun `should return NOT FOUND if the name does not exist`() {
            // given
            val name = "does_not_exist"

            // when / then
            mockMvc.get("$baseUrl/$name").andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }
    
    @Nested
    @DisplayName("POST /api/lessons")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewLesson {
        @Test
        fun `should add the new Lesson`() {
            // given
            val newLesson = Lesson(
                UUID.randomUUID(),
                "İş Sağlığı",
                Classroom(UUID.randomUUID(), "ED-98"),
                LocalDateTime.now(),
                "Recep Özkan",
                "Ömer Faruk Aydın",
                "mrb"
            )
            
            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newLesson)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newLesson))
                    }
                }

            mockMvc.get("$baseUrl/${newLesson.name}")
                .andExpect { content { json(objectMapper.writeValueAsString(newLesson)) } }

            
        }

        @Test
        fun `should return BAD REQUEST if Lesson with given name already exists`() {
            // given
            val invalidLesson = Lesson(
                UUID.randomUUID(),
                "Veri Analizi",
                Classroom(UUID.randomUUID(), "ED-7"),
                LocalDateTime.of(2022, 3, 13, 11, 0,0),
                "Emre Işık",
                "Ali",
                "slm"
            )

            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidLesson)
            }


            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }


    }


    @Nested
    @DisplayName("DELETE /api/lessons/{name}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingLesson{
        @Test
        @DirtiesContext
        fun `should delete the Lesson with the given name`() {
            // given
            val name = "Veri Analizi"

            // when/then
            mockMvc.delete("$baseUrl/$name")
                .andDo { print() }
                .andExpect { status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$name")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return NOT FOUND if no Lesson with given name exists`() {
            // given
            val invalidname = "does_not_exist"

            // when/then
            mockMvc.delete("$baseUrl/$invalidname")
                .andDo { print() }
                .andExpect { status{ isNotFound() } }

        }
    }


    @Nested
    @DisplayName("PATCH /api/lessons")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingLesson{
        @Test
        fun `should update an existing Lesson`() {
            // given
            val updatedLesson = Lesson(
                UUID.randomUUID(),
                "Yapay Zeka",
                Classroom(UUID.randomUUID(), "ED-54"),
                LocalDateTime.now(),
                "Canan Yıldız",
                "Ayşe Betül Yüce",
                "https://drive.google.com/file/d/1rX8emgtBYclyGg36t-7xH-oRCHiKBaZv/view"
            )

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedLesson)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedLesson))
                    }
                }

            // Lesson informations really updated,not tricked us by just returning our original object
            mockMvc.get("$baseUrl/${updatedLesson.name}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedLesson)) } }

        }

        @Test
        fun `should return BAD REQUEST if no Lesson with given name exists`() {
            // given
            val invalidLesson = Lesson(
                UUID.randomUUID(),
                "Türkçe",
                Classroom(UUID.randomUUID(), "ED-36"),
                LocalDateTime.of(2022, 6, 30, 12, 0,0),
                "Sercan İpek Ugay",
                "Mehmet",
                "nbr"
            )

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidLesson)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            //we except content to be empty too

        }
    }

}


