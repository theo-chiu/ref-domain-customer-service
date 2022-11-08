package io.ikigaidigital.accelerator.reference.domain.port.api

import io.ikigaidigital.accelerator.reference.domain.data.CustomerDto
import org.ikigai.customer.customerApi.Vv1.model.CreateCustomerDto
import org.ikigai.customer.customerApi.Vv1.model.CustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.ListCustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.UpdateCustomerDto

interface CustomerServicePort {
    fun getCustomers(): ListCustomerDtoResponse
    fun getCustomer(id: Long): CustomerDtoResponse
    fun createCustomer(customerDto: CreateCustomerDto)
    fun updateCustomer(id: Long, customerDto: UpdateCustomerDto)
    fun deleteCustomer(id: Long)
}