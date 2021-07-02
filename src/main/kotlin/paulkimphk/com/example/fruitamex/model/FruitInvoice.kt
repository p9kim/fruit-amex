package paulkimphk.com.example.fruitamex.model


data class FruitInvoice (
    val id: String,
    val appleCount: Int,
    val orangeCount: Int,
    val applesCost: Double,
    val orangesCost: Double,
    val totalCost: Double,
) {
    val applePrice: Double = 0.6
    val orangePrice: Double = 0.25
}