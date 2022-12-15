package com.example.tau.demo.controller

import com.example.tau.demo.model.Bus
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
internal class BusControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper:ObjectMapper
) {



    val baseUrl = "/api/buses"

    @Nested
    @DisplayName("GET /api/buses")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBuss {
        @Test
        fun `should return all buses `() {

            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$[0].departure_point") {
                        value("Kavacık")
                    }
                }

        }
    }

    @Nested
    @DisplayName("GET /api/buses/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBus{
        @Test
        fun `should return the Bus with the given id`() {
            // given
            val id = 3

            // when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.departure_point") {value("Türk Alman Üniversitesi")}
                }

        }

        @Test
        fun `should return NOT FOUND if the id does not exist`() {
            // given
            val id = 1000000

            // when / then
            mockMvc.get("$baseUrl/$id").andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }

    @Nested
    @DisplayName("POST /api/buses")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBus {
        @Test
        fun `should add the new Bus`() {
            // given
            val newBus = Bus(0, LocalDateTime.of(2035, 12, 12, 12, 0),"Kavacık")

            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBus)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBus))
                    }
                }

            mockMvc.get("$baseUrl/${newBus.ID}")
                .andExpect { content { json(objectMapper.writeValueAsString(newBus)) } }


        }

        @Test
        fun `should return BAD REQUEST if Bus with given id already exists`() {
            // given
            val invalidBus = Bus(100, LocalDateTime.of(2022, 12, 12, 12, 0),"Kavacık")

            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBus)
            }


            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }


    }


    @Nested
    @DisplayName("DELETE /api/buses/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingBus{
        @Test
        @DirtiesContext
        fun `should delete the Bus with the given id`() {
            // given
            val id = 1

            // when/then
            mockMvc.delete("$baseUrl/$id")
                .andDo { print() }
                .andExpect { status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$id")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return NOT FOUND if no Bus with given id exists`() {
            // given
            val invalidId =  900000000

            // when/then
            mockMvc.delete("$baseUrl/$invalidId")
                .andDo { print() }
                .andExpect { status{ isNotFound() } }

        }
    }


    @Nested
    @DisplayName("PATCH /api/buses")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingBus{
        @Test
        fun `should update an existing Bus`() {
            /*
            // given
            val updatedBus = Bus(0, LocalDateTime.of(2049, 12, 12, 12, 0),"Beykpz")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBus)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBus))
                    }
                }

            // Bus informations really updated,not tricked us by just returning our original object
            mockMvc.get("$baseUrl/${updatedBus.ID}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBus)) } }
            /*
        }

        @Test
        fun `should return BAD REQUEST if no Bus with given id exists`() {

             */
            // given
            val invalidBus = Bus(200, LocalDateTime.of(2022, 12, 12, 12, 0),"Kavacık")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBus)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            //we except content to be empty too

             */

        }


    }

}


