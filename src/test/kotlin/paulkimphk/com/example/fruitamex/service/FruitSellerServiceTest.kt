package paulkimphk.com.example.fruitamex.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance.Lifecycle
import paulkimphk.com.example.fruitamex.datasource.FruitInvoiceDataSource
import paulkimphk.com.example.fruitamex.model.FruitInvoice

internal class FruitSellerServiceTest {

    private val dataSource: FruitInvoiceDataSource = mockk(relaxed = true)

    private val fruitSellerService = FruitSellerService(dataSource)

    @Nested
    @DisplayName("getFruitInvoices() service")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetFruitInvoices {

        @Test
        fun `should verify one call to data source retrieveFruitInvoices()`() {
            // when
            fruitSellerService.getFruitInvoices()

            // then
            verify(exactly = 1) { dataSource.retrieveFruitInvoices() }
        }

    }

    @Nested
    @DisplayName("getFruitInvoice() service")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetFruitInvoice {

        @Test
        fun `should verify one call to data source retrieveFruitInvoice(id) is made`() {
            // given
            val id = "abcd"
            // when
            fruitSellerService.getFruitInvoice(id)

            // then
            verify(exactly = 1) { dataSource.retrieveFruitInvoice(id) }

         }

    }
    

    


}