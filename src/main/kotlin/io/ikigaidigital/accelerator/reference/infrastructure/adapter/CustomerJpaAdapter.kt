package io.ikigaidigital.accelerator.reference.infrastructure.adapter

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import io.ikigaidigital.accelerator.reference.domain.port.persistence.CustomerPersistencePort
import io.ikigaidigital.accelerator.reference.infrastructure.mapper.CustomerMapper
import io.ikigaidigital.accelerator.reference.infrastructure.repository.CustomerRepository
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class CustomerJpaAdapter(
    private val customerRepository: CustomerRepository,
    private val mapper: CustomerMapper
) : CustomerPersistencePort {

    override fun listCustomer(): List<CustomerDto>  {
        return customerRepository.findAll().stream().map { mapper.map(it) }.toList()
    }

    override fun getCustomer(id: Long): CustomerDto? {
        return customerRepository.findById(id).map {
            mapper.map(it)
        }.orElse(null)
    }

    override fun createCustomer(customerDto: CustomerDto): CustomerDto {
        val customer = mapper.map(customerDto)
        return mapper.map(customerRepository.save(customer))
    }

    override fun updateCustomer(id: Long, customerDto: CustomerDto): CustomerDto {
        val customer = customerRepository.findById(id).map {
            it.name = customerDto.name
            it.email = customerDto.email
            it.tier = customerDto.tier
            it
        }
        return mapper.map(customerRepository.save(customer.get()))
    }

    override fun deleteCustomer(id: Long) {
        customerRepository.deleteById(id)
    }
}