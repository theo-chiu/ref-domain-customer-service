package io.ikigaidigital.accelerator.reference.infrastructure.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "customer_point")
data class CustomerPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name="order_number", nullable = false)
    var orderNumber: String = "",
    @Column(name="points", nullable = false)
    var points: Int = 0,
    @Column(name = "date", nullable = false)
    var date: LocalDate = LocalDate.now(),
    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer? = null
)