package io.ikigaidigital.accelerator.reference.infrastructure.repository

import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import io.ikigaidigital.accelerator.reference.infrastructure.entity.CustomerPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerPointRepository: JpaRepository<CustomerPoint, Long>