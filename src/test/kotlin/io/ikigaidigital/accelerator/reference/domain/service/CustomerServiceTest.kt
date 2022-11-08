package io.ikigaidigital.accelerator.reference.domain.service

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import io.ikigaidigital.accelerator.reference.domain.data.enum.CustomerTier
import io.ikigaidigital.accelerator.reference.domain.port.persistence.CustomerPersistencePort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.ikigai.customer.customerApi.Vv1.model.CreateCustomerDto
import org.ikigai.customer.customerApi.Vv1.model.UpdateCustomerDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CustomerServiceTest {
    private lateinit var customerService: CustomerService
    private val customerPersistencePort = mockk<CustomerPersistencePort>()

    @BeforeEach
    fun setUp() {
        customerService = CustomerService(
            customerPersistencePort
        )
    }

    @Test
    fun `test listCustomer`() {
        every {
            customerPersistencePort.listCustomer()
        } returns listOf(
            CustomerDto(
                id = 1,
                name = "test-customer-1",
                email = "abc@gmail.com",
                tier = CustomerTier.GOLD.name
            )
        )

        customerService.getCustomers().let {
            assertThat(it).isNotNull
        assertThat(it.customers).isNotNull
            assertThat(it.customers).hasSize(1)
            assertThat(it.customers[0].id).isEqualTo(1)
            assertThat(it.customers[0].name).isEqualTo("test-customer-1")
            assertThat(it.customers[0].email).isEqualTo("abc@gmail.com")
            assertThat(it.customers[0].tier).isEqualTo(CustomerTier.GOLD.name)
        }
    }

    @Test
    fun `test getCustomer`() {
        every {
            customerPersistencePort.getCustomer(1)
        } returns CustomerDto(
            id = 1,
            name = "test-customer-1",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name
        )

        customerService.getCustomer(1).let {
            assertThat(it).isNotNull
            assertThat(it.customer).isNotNull
            assertThat(it.customer.id).isEqualTo(1)
            assertThat(it.customer.name).isEqualTo("test-customer-1")
            assertThat(it.customer.email).isEqualTo("abc@gmail.com")
        }
    }

    @Test
    fun `test createCustomer`() {
        every {
            customerPersistencePort.createCustomer(any())
        } returns CustomerDto(
            id = 1,
            name = "test-customer-1",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name
        )

        customerService.createCustomer(
            CreateCustomerDto(
                name = "test-customer-1",
                email = "abc@gmail.com"
            )
        )

        verify { customerPersistencePort.createCustomer(any()) }
    }

    @Test
    fun `test updateCustomer`() {
        every {
            customerPersistencePort.getCustomer(1)
        } returns CustomerDto(
            id = 1,
            name = "test-customer-1",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name
        )

        every {
            customerPersistencePort.updateCustomer(eq(1), any())
        } returns CustomerDto(
            id = 1,
            name = "test-customer-1",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name
        )

        customerService.updateCustomer(
            1,
            UpdateCustomerDto(
                name = "test-customer-1",
                email = "abc@gmail.com"
            )
        )

        verify {
            customerPersistencePort.updateCustomer(eq(1), any())
        }
    }

    @Test
    fun `test deleteCustomer`() {
        every {
            customerPersistencePort.deleteCustomer(any())
        } returns Unit

        customerService.deleteCustomer(1)

        verify {
            customerPersistencePort.deleteCustomer(1)
        }
    }
}