package paulkimphk.com.example.fruitamex

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorld {

    @GetMapping("/hellothere")
    fun helloWorld(): String = "Kenobi, you are a bold ONE~!"
}