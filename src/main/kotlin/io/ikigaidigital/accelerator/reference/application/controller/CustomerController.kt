package io.ikigaidigital.accelerator.reference.application.controller

import io.ikigaidigital.accelerator.reference.domain.port.api.CustomerServicePort
import org.ikigai.customer.customerApi.Vv1.CustomersApi
import org.ikigai.customer.customerApi.Vv1.model.CreateCustomerDto
import org.ikigai.customer.customerApi.Vv1.model.CustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.ListCustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.UpdateCustomerDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(
    private val customerService: CustomerServicePort
): CustomersApi {
    override fun getCustomers(): ResponseEntity<ListCustomerDtoResponse> {
        val customers = customerService.getCustomers()
        return ResponseEntity(customers, HttpStatus.OK)
    }

    override fun getCustomer(id: Long): ResponseEntity<CustomerDtoResponse> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    override fun createCustomer(createCustomerDto: CreateCustomerDto): ResponseEntity<Unit> {
        customerService.createCustomer(createCustomerDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun updateCustomer(id: Long, updateCustomerDto: UpdateCustomerDto): ResponseEntity<Unit> {
        customerService.updateCustomer(id, updateCustomerDto)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun deleteCustomer(id: Long): ResponseEntity<Unit> {
        customerService.deleteCustomer(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}