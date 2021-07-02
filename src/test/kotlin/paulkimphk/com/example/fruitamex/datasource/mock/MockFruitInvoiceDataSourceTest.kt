package paulkimphk.com.example.fruitamex.datasource.mock

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import paulkimphk.com.example.fruitamex.model.FruitInvoice

internal class MockFruitInvoiceDataSourceTest {
    private val mockData = MockFruitInvoiceDataSource()

    @Test
    fun `should return true that the collection of banks is not empty`() {
        // when
        val invoices = mockData.retrieveFruitInvoices()

        // then
        assertNotEquals(0, invoices.size)
    }

    @Test
    fun `should return invoice with given valid ID`() {
        // when
        val invoice = mockData.retrieveFruitInvoice("a12b")

        // then
        assertNotEquals(NoSuchElementException::class, invoice)
     }

    @Test
    fun `should the invoice that was added to datasource`() {
        // given
        val invoice = FruitInvoice("ccc33", 10, 8, 6.0, 2.0, 8.0)

        // when
        val addedInvoice = mockData.storeFruitInvoice(invoice)

        // then
        assertEquals(invoice, addedInvoice)
     }

    @Test
    fun `should return size of datasource increaed by 1`() {
        // given
        val invoice = FruitInvoice("ccc33", 10, 8, 6.0, 2.0, 8.0)

        // when
        mockData.storeFruitInvoice(invoice)

        // then
        assertEquals(4, mockData.retrieveFruitInvoices().size)
     }

}