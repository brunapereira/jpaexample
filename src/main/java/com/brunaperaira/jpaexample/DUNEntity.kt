package com.brunaperaira.jpaexample
import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Table(name = "dun")
@Entity(name = "DUN")
class DUNEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    var ean: BigInteger,
    var quantity: Int,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "product_id")
    var product: ProductEntity
)
