package io.ikigaidigital.accelerator.reference.infrastructure.adapter

import io.ikigaidigital.accelerator.reference.domain.data.enum.CustomerTier
import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import io.ikigaidigital.accelerator.reference.infrastructure.mapper.impl.CustomerMapperImpl
import io.ikigaidigital.accelerator.reference.infrastructure.repository.CustomerRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class CustomerJpaAdapterTest {
    private lateinit var customerJpaAdapter: CustomerJpaAdapter
    private val customerRepository = mockk<CustomerRepository>()
    @BeforeEach
    fun setUp() {
        customerJpaAdapter = CustomerJpaAdapter(
            customerRepository,
            mapper = CustomerMapperImpl()
        )
    }
    @Test
    fun `test listCustomer`() {
        every {
            customerRepository.findAll()
        } returns listOf(
            Customer(
                id = 1,
                name = "test-customer-1",
                email = "abc@gmail.com",
                tier = CustomerTier.GOLD.name
            )
        )

        customerJpaAdapter.listCustomer().let {
            assertThat(it).isNotNull
            assertThat(it.size).isEqualTo(1)
            assertThat(it[0].id).isEqualTo(1)
            assertThat(it[0].name).isEqualTo("test-customer-1")
            assertThat(it[0].email).isEqualTo("abc@gmail.com")
            assertThat(it[0].tier).isEqualTo(CustomerTier.GOLD.name)
        }
    }

    @Test
    fun `test getCustomer`() {
        every {
            customerRepository.findById(1)
        } returns Optional.of(Customer(
            id = 1,
            name = "test-customer-1",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name
        ))

        customerJpaAdapter.getCustomer(1).let {
            assertThat(it!!.id).isEqualTo(1)
            assertThat(it!!.name).isEqualTo("test-customer-1")
            assertThat(it!!.email).isEqualTo("abc@gmail.com")
            assertThat(it!!.tier).isEqualTo(CustomerTier.GOLD.name)
        }
    }
}