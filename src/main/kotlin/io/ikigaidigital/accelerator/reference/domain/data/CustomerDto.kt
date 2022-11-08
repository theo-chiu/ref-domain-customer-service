package io.ikigaidigital.accelerator.reference.domain.data

import org.ikigai.customer.customerApi.Vv1.model.GetCustomerDto
import java.math.BigDecimal

data class CustomerDto (
    val id: Long = 0,
    var name: String = "",
    var email: String = "",
    var tier: String = "",
    var points: Int = 0
) {
    fun toResponse(): GetCustomerDto {
        return GetCustomerDto(
            id = id,
            name = name,
            email = email,
            tier = tier,
            points = BigDecimal(points)
        )
    }
}