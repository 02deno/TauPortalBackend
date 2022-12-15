package com.example.tau.demo.controller

import com.example.tau.demo.model.Meal
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
internal class MealControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper:ObjectMapper
) {



    val baseUrl = "/api/meals"

    @Nested
    @DisplayName("GET /api/meals")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetMeals {
        @Test
        fun `should return all meals `() {

            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$[0].date") {
                        value("2022-12-12T11:30:00")
                    }
                }

        }
    }

    @Nested
    @DisplayName("GET /api/meals/{date}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetMeal{
        @Test
        fun `should return the meal with the given date`() {
            // given
            val date = "2022-12-14T11:30:00"

            // when/then
            mockMvc.get("$baseUrl/$date")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.date") {value("2022-12-14T11:30:00")}
                }

        }

        @Test
        fun `should return NOT FOUND if the meal does not exist`() {
            // given
            val date = "2022-12-12T11:30:08"

            // when / then
            mockMvc.get("$baseUrl/$date").andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }

    @Nested
    @DisplayName("POST /api/meals")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewMeal {
        @Test
        fun `should add the new meal`() {
            // given
            val newMeal = Meal(
                UUID.randomUUID(),
                LocalDateTime.of(2023, 12, 14,11,30),
                550)

            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newMeal)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newMeal))
                    }
                }

            mockMvc.get("$baseUrl/${newMeal.date}")
                .andExpect { content { json(objectMapper.writeValueAsString(newMeal)) } }


        }

        @Test
        fun `should return BAD REQUEST if Meal with given date already exists`() {
            // given
            val invalidMeal = Meal(
                UUID.randomUUID(),
                LocalDateTime.of(2023, 12, 14,11,30),
                550)

            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidMeal)
            }


            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }


    }


    @Nested
    @DisplayName("DELETE /api/meals/{date}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingMeal{
        @Test
        @DirtiesContext
        fun `should delete the meal with the given date`() {
            // given
            val date = "2022-12-14T11:30:00"

            // when/then
            mockMvc.delete("$baseUrl/$date")
                .andDo { print() }
                .andExpect { status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$date")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return NOT FOUND if no meal with given date exists`() {
            // given
            val invalidDate = "2022-12-12T11:30:07"

            // when/then
            mockMvc.delete("$baseUrl/$invalidDate")
                .andDo { print() }
                .andExpect { status{ isNotFound() } }

        }
    }


    @Nested
    @DisplayName("PATCH /api/meals")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingMeal{
        @Test
        fun `should update an existing meal`() {
            // given
            val updatedMeal = Meal(
                UUID.randomUUID(),
                LocalDateTime.of(2022, 12, 12,11,30),
                555)

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedMeal)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedMeal))
                    }
                }

            // Meal informations really updated,not tricked us by just returning our original object
            mockMvc.get("$baseUrl/${updatedMeal.date}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedMeal)) } }

        }

        @Test
        fun `should return BAD REQUEST if no meal with given date exists`() {
            // given
            val invalidMeal = Meal(
                UUID.randomUUID(),
                LocalDateTime.of(2030, 12, 14,11,30),
                555)


            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidMeal)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            //we except content to be empty too

        }
    }

}
