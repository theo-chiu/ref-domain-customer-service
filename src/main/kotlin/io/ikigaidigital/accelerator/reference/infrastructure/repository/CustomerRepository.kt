package io.ikigaidigital.accelerator.reference.infrastructure.repository

import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {

    @EntityGraph(value = "Customer.customerPoints", type = EntityGraph.EntityGraphType.LOAD)
    override fun findAll(): List<Customer>
}