package paulkimphk.com.example.fruitamex.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import paulkimphk.com.example.fruitamex.model.FruitData
import paulkimphk.com.example.fruitamex.model.FruitInvoice
import paulkimphk.com.example.fruitamex.service.FruitSellerService
import kotlin.IllegalArgumentException

@RestController
@RequestMapping("/api/fruitshop")
class FruitSellerController(private val service: FruitSellerService) {

    // Handles error where a fruit invoice that doesn't exist is attempted to be accessed
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    // When negative number of apples or oranges are used as inputs
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    // Calculate cost and store invoice in database with genereated id using URL Params
    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    fun calculateFruitsCostParam(@RequestParam("apples") apples: Int, @RequestParam("oranges") oranges: Int,
                                 @RequestParam("applePromo") applePromo: Boolean,
                                 @RequestParam("orangePromo") orangePromo: Boolean): FruitInvoice {
        return service.calculateAndStoreFruitsCost(apples, oranges, applePromo, orangePromo)
    }

    // Calculate cost and store invoice in database with generated id using json post request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun calculateFruitsCostJSON(@RequestBody data: FruitData): FruitInvoice {
        return service.calculateAndStoreFruitsCost(data.appleCount, data.orangeCount, data.applePromo, data.orangePromo)
    }

    // Get all the invoices from database
    @GetMapping
    fun getFruitInvoices(): Collection<FruitInvoice> = service.getFruitInvoices()

    // Get fruit invoice by id
    @GetMapping("/{invoiceId}")
    fun getFruitInvoice(@PathVariable invoiceId: String) = service.getFruitInvoice(invoiceId)
}