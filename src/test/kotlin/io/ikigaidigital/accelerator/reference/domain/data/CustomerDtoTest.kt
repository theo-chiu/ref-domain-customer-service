package io.ikigaidigital.accelerator.reference.domain.data

import io.ikigaidigital.accelerator.reference.domain.data.enum.CustomerTier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CustomerDtoTest {
    @Test
    fun `should map customer to response`() {
        val customer = CustomerDto(
            id = 1,
            name = "test",
            email = "abc@gmail.com",
            tier = CustomerTier.GOLD.name,
            points = 300
        )

        customer.toResponse().let {
            assertThat(it.id).isEqualTo(customer.id)
            assertThat(it.name).isEqualTo(customer.name)
            assertThat(it.email).isEqualTo(customer.email)
            assertThat(it.tier).isEqualTo(customer.tier)
            assertThat(it.points).isEqualTo(BigDecimal(customer.points))
        }
    }
}