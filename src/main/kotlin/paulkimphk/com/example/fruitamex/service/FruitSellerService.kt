package paulkimphk.com.example.fruitamex.service

import org.springframework.stereotype.Service
import paulkimphk.com.example.fruitamex.model.FruitInvoice
import java.lang.IllegalArgumentException

@Service
class FruitSellerService {
    private val applePrice = .6
    private val orangePrice = .25

    fun calculateFruitsCost(apples: Int, oranges: Int, applePromo: Boolean, orangePromo: Boolean): FruitInvoice {
        // Negative numbers will give undesirable results for a fruit shop
        if (apples < 0 || oranges < 0) {
            throw IllegalArgumentException("Invalid number of apples or oranges")
        }

        // Calculate the cost of fruits based on if promotions are going on
        var applesTotal = 0.0
        var orangesTotal = 0.0
        if (applePromo) {
            val applesLeftOver = apples % 2
            val applesPromoCost = ((apples - applesLeftOver) / 2) * applePrice
            val applesLeftOverCost = applesLeftOver * applePrice
            applesTotal = applesPromoCost + applesLeftOverCost
        } else {
            applesTotal = apples * applePrice
        }

        if (orangePromo) {
            val orangesLeftOver = oranges % 3
            val orangesPromoCost = ((oranges - orangesLeftOver) / 3) * (orangePrice * 2)
            val orangesLeftOverCost = (oranges % 3) * orangePrice
            orangesTotal = orangesPromoCost + orangesLeftOverCost
        } else {
            orangesTotal = oranges * orangePrice
        }

        val costTotal = applesTotal + orangesTotal

        // Creates the invoice to be returned
        val invoice = FruitInvoice(apples, oranges, applesTotal,
                                orangesTotal, costTotal)
        return invoice
    }
}