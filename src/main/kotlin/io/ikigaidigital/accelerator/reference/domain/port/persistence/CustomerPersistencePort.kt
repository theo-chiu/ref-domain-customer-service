package io.ikigaidigital.accelerator.reference.domain.port.persistence

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import org.springframework.stereotype.Repository

@Repository
interface CustomerPersistencePort {
    fun listCustomer(): List<CustomerDto>
    fun getCustomer(id: Long): CustomerDto?
    fun createCustomer(customerDto: CustomerDto): CustomerDto
    fun updateCustomer(id: Long, customerDto: CustomerDto): CustomerDto
    fun deleteCustomer(id: Long)
}