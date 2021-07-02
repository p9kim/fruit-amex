package paulkimphk.com.example.fruitamex.model

import com.fasterxml.jackson.annotation.JsonProperty

// For serializing and deserializing incoming json datd requests
data class FruitData(
    @JsonProperty("apple_count")
    val appleCount: Int,

    @JsonProperty("orange_count")
    val orangeCount: Int,

    @JsonProperty("apple_promo")
    val applePromo: Boolean,

    @JsonProperty("orange_promo")
    val orangePromo: Boolean,
)