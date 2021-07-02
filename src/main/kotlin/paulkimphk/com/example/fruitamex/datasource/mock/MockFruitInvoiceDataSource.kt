package paulkimphk.com.example.fruitamex.datasource.mock

import org.springframework.stereotype.Repository
import paulkimphk.com.example.fruitamex.datasource.FruitInvoiceDataSource
import paulkimphk.com.example.fruitamex.model.FruitInvoice

@Repository
class MockFruitInvoiceDataSource: FruitInvoiceDataSource {
    val fruitInvoices = mutableListOf(
        FruitInvoice("1234",4, 6, 4.8, 2.0, 6.8),
        FruitInvoice("abcd", 10, 8, 6.0, 2.0, 8.0),
        FruitInvoice("a12b",15, 9, 9.0, 1.5, 10.5),
    )

    override fun retrieveFruitInvoices(): Collection<FruitInvoice> = fruitInvoices

    override fun retrieveFruitInvoice(invoiceId: String): FruitInvoice {
        return fruitInvoices.firstOrNull() { it.id == invoiceId }
            ?: throw NoSuchElementException("An invoice by this id does not exist")
    }

    override fun storeFruitInvoice(invoice: FruitInvoice): FruitInvoice {
        fruitInvoices.add(invoice)
        return invoice
    }
}