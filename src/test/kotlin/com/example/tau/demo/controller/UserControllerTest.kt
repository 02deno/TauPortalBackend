package com.example.tau.demo.controller

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
internal class UserControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper:ObjectMapper
) {



    val baseUrl = "/api/users"

    @Nested
    @DisplayName("GET /api/users")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetUsers {
        @Test
        fun `should return all users `() {

            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$[0].name") {
                        value("Deniz")
                    }
                }

        }
    }

    @Nested
    @DisplayName("GET /api/users/{mail}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetUser{
        @Test
        fun `should return the user with the given mail`() {
            // given
            val mail = "dila@gmail.com"

            // when/then
            mockMvc.get("$baseUrl/$mail")
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
    @DisplayName("POST /api/users")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewUser {
        @Test
        fun `should add the new user`() {
            // given
            val newUser = User(
                UUID.randomUUID(),
                "Ayşe",
                "Derli",
                "ayse@gmail.com",
                "123",
                "05385427348",
                "student")
            
            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newUser)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newUser))
                    }
                }

            mockMvc.get("$baseUrl/${newUser.mail}")
                .andExpect { content { json(objectMapper.writeValueAsString(newUser)) } }

            
        }

        @Test
        fun `should return BAD REQUEST if user with given gmail already exists`() {
            // given
            val invalidUser = User(
                UUID.randomUUID(),
                "Helga",
                "Dinç",
                "hasan@gmail.com",
                "123",
                "05385427348",
                "student")

            // when
            val performPost = mockMvc.post("$baseUrl/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidUser)
            }


            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }


    }


    @Nested
    @DisplayName("DELETE /api/users/{mail}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingUser{
        @Test
        @DirtiesContext
        fun `should delete the user with the given mail`() {
            // given
            val mail = "hasan@gmail.com"

            // when/then
            mockMvc.delete("$baseUrl/$mail")
                .andDo { print() }
                .andExpect { status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$mail")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return NOT FOUND if no user with given mail exists`() {
            // given
            val invalidMail = "does_not_exist"

            // when/then
            mockMvc.delete("$baseUrl/$invalidMail")
                .andDo { print() }
                .andExpect { status{ isNotFound() } }

        }
    }


    @Nested
    @DisplayName("PATCH /api/users")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingUser{
        @Test
        fun `should update an existing user`() {
            // given
            val updatedUser = User(
                UUID.randomUUID(),
                "Hasan Can",
                "Dizdar",
                "hasan@gmail.com",
                "12354",
                "0538",
                "student")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedUser)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedUser))
                    }
                }

            // bank informations really updated,not tricked us by just returning our original object
            mockMvc.get("$baseUrl/${updatedUser.mail}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedUser)) } }

        }

        @Test
        fun `should return BAD REQUEST if no user with given mail exists`() {
            // given
            val invalidUser = User(
                UUID.randomUUID(),
                "Hasan Can",
                "Dizdar",
                "does_not_exist@gmail.com",
                "12354",
                "0538",
                "student")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidUser)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            //we except content to be empty too

        }
    }

}


