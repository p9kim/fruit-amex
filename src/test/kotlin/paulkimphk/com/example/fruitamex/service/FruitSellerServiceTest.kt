package paulkimphk.com.example.fruitamex.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import paulkimphk.com.example.fruitamex.model.FruitInvoice

internal class FruitSellerServiceTest {

    private val fruitSellerService = FruitSellerService()

    @Test
    fun `should return the total regular cost of apples and oranges`() {
        // given
        val apples = 10
        val oranges = 4
        
        // when
        val totalInvoice = fruitSellerService.calculateFruitsCost(apples, oranges, false, false)
        
        // then
        val checkInvoice = FruitInvoice(10, 4, 6.0, 1.0, 7.0)
        Assertions.assertEquals(checkInvoice, totalInvoice)
     }

    @Test
    fun `Total cost should take into account promo sales`() {
        // given
        val appleOddCount = 15
        val orangesOddCount1 = 11
        val orangesOddCount2 = 9
        val appleEvenCount = 8
        val orangesEvenCount = 16

        // when
        val promoOddCost1Invoice = fruitSellerService.calculateFruitsCost(appleOddCount, orangesOddCount1, true, true)
        val promoEvenCostInvoice =  fruitSellerService.calculateFruitsCost(appleEvenCount, orangesEvenCount, true, true)
        val promoOddCost2Invoice = fruitSellerService.calculateFruitsCost(appleOddCount, orangesOddCount2, false, true)

        // then
        val promoOddCost1Check = FruitInvoice(appleOddCount, orangesOddCount1, 4.8, 2.0, 6.8)
        val promoEvenCostCheck = FruitInvoice(appleEvenCount, orangesEvenCount, 2.4, 2.75, 5.15)
        val promoOddCost2Check = FruitInvoice(appleOddCount, orangesOddCount2, 9.0, 1.5, 10.5)
        Assertions.assertEquals(promoOddCost1Check, promoOddCost1Invoice)
        Assertions.assertEquals(promoEvenCostCheck, promoEvenCostInvoice)
        Assertions.assertEquals(promoOddCost2Check, promoOddCost2Invoice)
     }

}