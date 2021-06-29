package paulkimphk.com.example.fruitamex.service

import org.springframework.stereotype.Service
import paulkimphk.com.example.fruitamex.model.FruitInvoice
import java.lang.IllegalArgumentException

@Service
class FruitSellerService {
    private val applePrice = .6
    private val orangePrice = .25

    fun calculateFruitsCost(apples: Int, oranges: Int): FruitInvoice {
        if (apples < 0 || oranges < 0) {
            throw IllegalArgumentException("Invalid number of apples or oranges")
        }

        // Calculates the total cost
        val appleTotal = apples * applePrice
        val orangesTotal = oranges * orangePrice
        val costTotal = appleTotal + orangesTotal

        // Creates the invoice to be returned
        val invoice = FruitInvoice(applePrice = applePrice, orangePrice = orangePrice, appleCount = apples, orangeCount = oranges, applesCost = appleTotal,
                                orangesCost = orangesTotal, totalCost = costTotal)
        return invoice
    }
}