package io.ikigaidigital.accelerator.reference

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class RefDomainCustomerServiceApplication

fun main(args: Array<String>) {
    runApplication<RefDomainCustomerServiceApplication>(*args)
}
