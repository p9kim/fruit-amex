package paulkimphk.com.example.fruitamex.datasource

import paulkimphk.com.example.fruitamex.model.FruitInvoice


interface FruitInvoiceDataSource {
    // Get all the Fruit invoices in data base
    fun retrieveFruitInvoices(): Collection<FruitInvoice>
    // Get Fruit invoice by id
    fun retrieveFruitInvoice(invoiceId: String): FruitInvoice
    // store fruit invoice in database
    fun storeFruitInvoice(invoice: FruitInvoice): FruitInvoice
}