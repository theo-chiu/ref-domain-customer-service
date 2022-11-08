package io.ikigaidigital.accelerator.reference.sst

import io.ikigaidigital.accelerator.reference.infrastructure.entity.Customer
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.ikigai.customer.customerApi.Vv1.model.CustomerDtoResponse
import org.ikigai.customer.customerApi.Vv1.model.ListCustomerDtoResponse
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap

class CustomerSst(
    @LocalServerPort
    private val port: Int
) : SstBase() {
    private val baseUrl = "http://localhost:$port/api/v1"

    @Test
    fun `should get all customers`() {
        // setup
        RestAssured.port = port
        insertTestCustomerWithPoints()

        // execute
        val response = given().get("$baseUrl/customers").then()
            .statusCode(200)
            .assertThat()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract().`as`(ListCustomerDtoResponse::class.java)

        //verify
        with(response.customers) {
            assertThat(this).isNotNull
            assertThat(this.size).isEqualTo(1)
        }
    }

    @Test
    fun `should get particular customer`() {
        //setup
        insertTestCustomerWithPoints()

        // execute
        val customerId = customerRepository.findAll().first().id
        val url = "$baseUrl/customers/$customerId"
        val response = given().get(url).then()
            .statusCode(200)
            .assertThat()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract().`as`(CustomerDtoResponse::class.java)

        // verify
        with(response.customer) {
            assertThat(this).isNotNull
            assertThat(this.id).isEqualTo(customerId)
            assertThat(this.name).isEqualTo("Test Customer")
            assertThat(this.email).isEqualTo("abc@gmail.com")
            assertThat(this.tier).isEqualTo("GOLD")
            assertThat(this.points).isEqualTo(BigDecimal(350))
        }
    }

    @Test
    fun `should create customer`() {
        // setup
        val url = "$baseUrl/customers"
        var requestMap = HashMap<String, String>()
        requestMap["name"] = "Test Customer"
        requestMap["email"] = "abc@gmail.com"

        // execute
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(requestMap)
            .post(url)
            .then()
            .statusCode(201)

        // verify
        customerRepository.findAll().first().let {
            assertThat(it.name).isEqualTo("Test Customer")
            assertThat(it.email).isEqualTo("abc@gmail.com")
        }
    }

    @Test
    fun `should update customer`() {
        // setup
        insertTestCustomerWithPoints()
        val customerId = customerRepository.findAll().first().id
        val url = "$baseUrl/customers/$customerId"
        var requestMap = HashMap<String, String>()
        requestMap["name"] = "Test Customer 1"
        requestMap["email"] = "123@gmail.com"

        // execute
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(requestMap)
            .put(url)
            .then()
            .statusCode(200)

        // verify
        customerRepository.findAll().first().let {
            assertThat(it.name).isEqualTo("Test Customer 1")
            assertThat(it.email).isEqualTo("123@gmail.com")
        }
    }

    @Test
    fun `should delete customer`() {
        // setup
        insertTestCustomerWithPoints()
        val customerId = customerRepository.findAll().first().id
        val url = "$baseUrl/customers/$customerId"

        // execute
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .delete(url)
            .then()
            .statusCode(204)

        // verify
        assertThat(customerRepository.findById(customerId)).isEqualTo(Optional.empty<Customer>())
    }
}