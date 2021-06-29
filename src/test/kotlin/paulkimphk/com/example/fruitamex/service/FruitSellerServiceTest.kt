package paulkimphk.com.example.fruitamex.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

internal class FruitSellerServiceTest {

    private val fruitSellerService = FruitSellerService()

    @Test
    fun `should return the total cost of apples and oranges`() {
        // given
        val apples = 10
        val oranges = 4
        
        // when
        val total = fruitSellerService.calculateFruitsCost(apples, oranges)
        
        // then
        Assertions.assertEquals(7.0, total)
     }

}