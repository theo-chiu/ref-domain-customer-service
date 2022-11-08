package io.ikigaidigital.accelerator.reference.domain.service

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import io.ikigaidigital.accelerator.reference.domain.port.api.CustomerServicePort
import io.ikigaidigital.accelerator.reference.domain.port.persistence.CustomerPersistencePort
import org.ikigai.customer.customerApi.Vv1.model.CreateCustomerDto
import org.ikigai.customer.customerApi.Vv1.model.CustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.ListCustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.UpdateCustomerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CustomerService(
    private val customerPersistencePort: CustomerPersistencePort
) : CustomerServicePort {
    override fun getCustomers(): ListCustomerDtoResponse {
        val customers = customerPersistencePort.listCustomer()
        return ListCustomerDtoResponse(
            customers = customers.map { it.toResponse() }
        )
    }

    override fun getCustomer(id: Long): CustomerDtoResponse {
        val customer = customerPersistencePort.getCustomer(id)
            ?: throw Exception("Customer with id $id not found")
        return CustomerDtoResponse(
            customer = customer.toResponse()
        )
    }

    override fun createCustomer(customerDto: CreateCustomerDto) {
        val customer = CustomerDto(
            name = customerDto.name,
            email = customerDto.email
        )
        customerPersistencePort.createCustomer(customer)
    }

    override fun updateCustomer(id: Long, customerDto: UpdateCustomerDto) {
        val customer = customerPersistencePort.getCustomer(id)
            ?: throw Exception("Customer with id $id not found")
        customer.name = customerDto.name
        customer.email = customerDto.email
        customerPersistencePort.updateCustomer(id, customer)
    }

    override fun deleteCustomer(id: Long) {
        customerPersistencePort.deleteCustomer(id)
    }
}