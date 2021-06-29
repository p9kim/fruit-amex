package paulkimphk.com.example.fruitamex.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.StreamingHttpOutputMessage
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class FruitSellerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    
    @Test
    fun `should return status ok, url can be reached`() {
        // when/then
        mockMvc.post("/api/fruitshop?apples=8&oranges=7")
            .andDo{ print() }
            .andExpect {
                status { isOk() }
            }
     }
    
    @Test
    fun `should return a summary invoice of the order`() {
        // when/then
        mockMvc.post("/api/fruitshop?apples=10&oranges=8")
            .andDo{ print() }
            .andExpect {
                status { isOk() }
                jsonPath("$.applePrice") { value(0.6) }
                jsonPath("$.orangePrice") { value(.25) }
                jsonPath("$.appleCount") { value(10) }
                jsonPath("$.orangeCount") { value(8) }
                jsonPath("$.applesCost") { value(6.0) }
                jsonPath("$.orangesCost") { value(2.0) }
                jsonPath("$.totalCost") { value(8.0) }
            }
        
     }
}