package io.ikigaidigital.accelerator.reference.sst

import io.ikigaidigital.accelerator.reference.domain.data.enum.CustomerTier
import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import io.ikigaidigital.accelerator.reference.infrastructure.entity.CustomerPoint
import io.ikigaidigital.accelerator.reference.infrastructure.repository.CustomerPointRepository
import io.ikigaidigital.accelerator.reference.infrastructure.repository.CustomerRepository
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SstBase {
    @BeforeEach
    fun setup() {
        customerPointRepository.deleteAll()
        customerRepository.deleteAll()
    }

    @Autowired
    internal lateinit var customerRepository: CustomerRepository

    @Autowired
    internal lateinit var customerPointRepository: CustomerPointRepository

    companion object {
        private const val testDbName = "ReferenceDomain"
        private const val testDbUser = "postgres"
        private const val testDbPw = "ikigai123"

        private val postgres = PostgreSQLContainer(DockerImageName.parse("postgres:12.3-alpine"))
            .apply {
                withDatabaseName(testDbName)
                withUsername(testDbUser)
                withPassword(testDbPw)
                start()
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.datasource.username", postgres::getUsername)
        }
    }

    fun insertTestCustomerWithPoints() {
        val addedCustomer = customerRepository.save(
            Customer(
                name = "Test Customer",
                email = "abc@gmail.com",
                tier = CustomerTier.GOLD.name
            )
        )

        customerPointRepository.saveAll(
            listOf(
                CustomerPoint(
                    orderNumber = "test-point-0001",
                    points = 150,
                    date = LocalDate.now(),
                    customer = addedCustomer
                ),
                CustomerPoint(
                    orderNumber = "test-point-0002",
                    points = 200,
                    date = LocalDate.now(),
                    customer = addedCustomer
                )
            )
        )
    }
}