package io.ikigaidigital.accelerator.reference.infrastructure.mapper

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer

interface CustomerMapper {
    fun map(customer: Customer): CustomerDto
    fun map(customerDto: CustomerDto): Customer
}