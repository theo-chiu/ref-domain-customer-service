package io.ikigaidigital.accelerator.reference.infrastructure.mapper.impl

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import io.ikigaidigital.accelerator.reference.infrastructure.mapper.CustomerMapper
import org.springframework.stereotype.Component

@Component
class CustomerMapperImpl: CustomerMapper {
    override fun map(customer: Customer): CustomerDto {
        return CustomerDto(
            id = customer.id,
            name = customer.name,
            email = customer.email,
            tier = customer.tier,
            points = customer.customerPoints.sumOf { it.points }
        )
    }

    override fun map(customerDto: CustomerDto): Customer {
        return Customer(
            id = customerDto.id,
            name = customerDto.name,
            email = customerDto.email,
            tier = customerDto.tier
        )
    }
}