package paulkimphk.com.example.fruitamex.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
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
import org.springframework.http.StreamingHttpOutputMessage
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import paulkimphk.com.example.fruitamex.model.FruitData
import paulkimphk.com.example.fruitamex.model.FruitInvoice

@SpringBootTest
@AutoConfigureMockMvc
internal class FruitSellerControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val url = "/api/fruitshop"
    
    @Nested
    @DisplayName("CALCULATE&STORE (params) /api/fruitseller/checkout")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class CalculateAndStoreFruitInvoicesWithParams {

        @Test
        fun `should return a summary invoice of the order without promo prices`() {
            // when/then
            mockMvc.post("$url/checkout?apples=10&oranges=8&applePromo=false&orangePromo=false")
                .andDo{ print() }
                .andExpect {
                    status { isCreated() }
                    jsonPath("$.applePrice") { value(0.6) }
                    jsonPath("$.orangePrice") { value(.25) }
                    jsonPath("$.appleCount") { value(10) }
                    jsonPath("$.orangeCount") { value(8) }
                    jsonPath("$.applesCost") { value(6.0) }
                    jsonPath("$.orangesCost") { value(2.0) }
                    jsonPath("$.totalCost") { value(8.0) }
                }
        }

        @Test
        fun `should return a summary of the order with promo prices`() {
            // when/then
            mockMvc.post("$url/checkout?apples=15&oranges=11&applePromo=true&orangePromo=true")
                .andDo{ print() }
                .andExpect {
                    status { isCreated() }
                    jsonPath("$.applePrice") { value(0.6) }
                    jsonPath("$.orangePrice") { value(.25) }
                    jsonPath("$.appleCount") { value(15) }
                    jsonPath("$.orangeCount") { value(11) }
                    jsonPath("$.applesCost") { value(4.8) }
                    jsonPath("$.orangesCost") { value(2.0) }
                    jsonPath("$.totalCost") { value(6.8) }
                }
        }

        @Test
        fun `should calculate, create invoice, and store that invoice in database`() {
            // when
            val postRes = mockMvc.post("$url/checkout?apples=20&oranges=16&applePromo=false&orangePromo=false")
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                }

            val result = postRes.andReturn()
            val stringRes = result.response.contentAsString
            val jsonRes = JSONObject(stringRes)
            val generatedId = jsonRes.get("id")
            
            // then
            mockMvc.get("$url/$generatedId")
                .andExpect {
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonRes
                    }
                }
            
        }
        
        @Test
        fun `should return BAD REQUEST if negative amount of apples and oranges is given`() {
            // when/then
            mockMvc.post("$url/checkout?apples=-7&oranges=-16&applePromo=false&orangePromo=false")
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
         }

    }

    @Nested
    @DisplayName("CALCULATE&STORE (json) /api/fruitshop")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class CalculateAndStoreFruitInvoicesJSON {

        @Test
        fun `should return generated and stored fruit invoice using json`() {
            // given
            val jsonData = FruitData(10, 20, true, false)

            // when
            val postReq = mockMvc.post("$url") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(jsonData)
            }

            val postRes = postReq
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                }
                .andReturn()

            val stringRes = postRes.response.contentAsString
            val jsonRes = JSONObject(stringRes)
            val generatedId = jsonRes.get("id")

            // then
            mockMvc.get("$url/$generatedId")
                .andExpect {
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonRes
                    }
                }

         }

        @Test
        fun `should return BAD REQUEST when negative apples and oranges are given`() {
            // given
            val data = FruitData(-4, 10, false, true)

            // when
            val postReq = mockMvc.post("$url") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(data)
            }

            // then
            postReq
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
         }

    }
    
    @Nested
    @DisplayName("GET /api/fruitshop")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetFruitInvoices {
    
        @Test
        fun `should return all the fruit invoices`() {
            // when/then
            mockMvc.get(url)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") { value("1234") }
                    jsonPath("$[2].id") { value("a12b") }
                }
         }
        
    }

    @Nested
    @DisplayName("GET /api/fruitshop/{invoiceId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetFruitInvoice {

        @Test
        fun `should return specified fruit invoice id`() {
            // given
            val id = "abcd"
            
            // when/then
            mockMvc.get("$url/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.appleCount") { value(10) }
                    jsonPath("$.orangeCount") { value(8) }
                    jsonPath("$.applesCost") { value(6.0) }
                    jsonPath("$.orangesCost") { value(2.0) }
                    jsonPath("$.totalCost") { value(8.0) }
                }
            
         }
        
        @Test
        fun `should return status NOT FOUND for invoice that does not exist`() {
            // given
            val badId = "no_id"
            
            
            // when/then
            mockMvc.get("$url/$badId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
            
         }

    }


}