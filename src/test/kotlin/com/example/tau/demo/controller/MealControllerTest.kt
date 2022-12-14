/*
package com.example.tau.demo.controller

import com.example.tau.demo.model.Meal
import com.example.tau.demo.model.User
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
                    jsonPath($meals.size) {
                        value(3)
                    }
                }

        }

    }

    @Nested
    @DisplayName("GET /api/meals/{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetUser{
        @Test
        fun `should return the user with the given id`() {
            // given
            val meal_id = "128kcal"

            // when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name") {value("Dilanur")}
                    jsonPath("$.surname") {value("Gider")}
                }

        }

        @Test
        fun `should return NOT FOUND if the mail does not exist`() {
            // given
            val mail = "does_not_exist"

            // when / then
            mockMvc.get("$baseUrl/$mail").andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }

    @Nested
    @DisplayName("POST /api/meals")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewUser {
        @Test
        fun `should add the new meal`() {
            // given
            val newMeal = Meal(
                UUID.randomUUID(),
                "2022-10-12",
                128)

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

            mockMvc.get("$baseUrl/${newMeal.mail}")
                .andExpect { content { json(objectMapper.writeValueAsString(newMeal)) } }


        }

        @Test
        fun `should return BAD REQUEST if meal with given gmail already exists`() {
            // given
            val invalidMeal = Meal(
                UUID.randomUUID(),
                "2022-09-06",
                300)

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
    @DisplayName("DELETE /api/meal/{cal}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingUser{
        @Test
        @DirtiesContext
        fun `should delete the meal with the given cal`() {
            // given
            val cal = "128"

            // when/then
            mockMvc.delete("$baseUrl/$cal")
                .andDo { print() }
                .andExpect { status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$cal")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return NOT FOUND if no meal with given cal exists`() {
            // given
            val invalidCal = "does_not_exist"

            // when/then
            mockMvc.delete("$baseUrl/$invalidCal")
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
                "2022-11-11",
                500,
                )

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

            // bank informations really updated,not tricked us by just returning our original object
            mockMvc.get("$baseUrl/${updatedMeal.cal}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedMeal)) } }

        }

        @Test
        fun `should return BAD REQUEST if no meal with given cal exists`() {
            // given
            val invalidMeal = Meal(
                UUID.randomUUID(),
                "2018-11-11",
                501)

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

*/
