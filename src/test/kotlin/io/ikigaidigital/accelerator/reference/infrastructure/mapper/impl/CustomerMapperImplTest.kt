package io.ikigaidigital.accelerator.reference.infrastructure.mapper.impl

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import io.ikigaidigital.accelerator.reference.domain.data.enum.CustomerTier
import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import io.ikigaidigital.accelerator.reference.infrastructure.entity.CustomerPoint
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CustomerMapperImplTest {
    @Test
    fun `should map customer to customerDto`() {
        val customer = Customer(
            id = 1,
            name = "test",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name,
            customerPoints = listOf(
                CustomerPoint(
                    id = 1,
                    points = 100,
                ),
                CustomerPoint(
                    id = 2,
                    points = 200,
                )
            )
        )

        CustomerMapperImpl().map(customer).let { dto ->
            assertThat(dto.id).isEqualTo(customer.id)
            assertThat(dto.name).isEqualTo(customer.name)
            assertThat(dto.email).isEqualTo(customer.email)
            assertThat(dto.tier).isEqualTo(customer.tier)
            assertThat(dto.points).isEqualTo(customer.customerPoints.sumOf { it.points })
        }
    }

    @Test
    fun `should map customerDto to customer`() {
        val customerDto = CustomerDto(
            id = 1,
            name = "test",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name,
            points = 300
        )

        CustomerMapperImpl().map(customerDto).let { response ->
            assertThat(response.id).isEqualTo(customerDto.id)
            assertThat(response.name).isEqualTo(customerDto.name)
            assertThat(response.email).isEqualTo(customerDto.email)
            assertThat(response.tier).isEqualTo(customerDto.tier)
        }
    }
}