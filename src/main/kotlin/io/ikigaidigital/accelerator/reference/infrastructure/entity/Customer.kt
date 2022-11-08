package io.ikigaidigital.accelerator.reference.infrastructure.entity

import javax.persistence.*

@NamedEntityGraphs(*[
    NamedEntityGraph(
        name = "Customer.customerPoints",
        attributeNodes = [
            NamedAttributeNode("customerPoints")
        ]
    )
])
@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name="name", nullable = false)
    var name: String = "",
    @Column(name="email", nullable = false)
    var email: String = "",
    @Column(name="tier", nullable = false)
    var tier: String = "",
    @OneToMany(
        targetEntity = CustomerPoint::class,
        cascade = [CascadeType.ALL],
        mappedBy = "customer"
    )
    var customerPoints: List<CustomerPoint> = listOf()
)