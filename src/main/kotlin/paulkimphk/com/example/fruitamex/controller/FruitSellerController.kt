package paulkimphk.com.example.fruitamex.controller

import org.springframework.web.bind.annotation.*
import paulkimphk.com.example.fruitamex.model.FruitInvoice
import paulkimphk.com.example.fruitamex.service.FruitSellerService

@RestController
@RequestMapping("/api/fruitshop")
class FruitSellerController(private val service: FruitSellerService) {

    @PostMapping
    fun calculateFruitsCost(@RequestParam("apples") apples: Int, @RequestParam("oranges") oranges: Int,
                            @RequestParam("applePromo") applePromo: Boolean,
                            @RequestParam("orangePromo") orangePromo: Boolean): FruitInvoice {
        return service.calculateFruitsCost(apples, oranges, applePromo, orangePromo)
    }
}