package com.brunaperaira.jpaexample

import java.math.BigInteger
import java.util.*

data class DUNPayload(
    val quantity: Int,
    val ean: BigInteger
)


data class ProductPayload(
    val id: UUID? = null,
    val name: String,
    val duns: List<DUNPayload>?
) {
    companion object {
        fun fromEntity(entity: ProductEntity): ProductPayload {
            return ProductPayload(
                id = entity.id,
                name = entity.name,
                duns = entity.duns.map { DUNPayload(ean=it.ean, quantity=it.quantity) }
            )
        }
    }

    fun toEntity(): ProductEntity {
        val entity = ProductEntity(
            name = name,
            duns = mutableSetOf()
        )

        val duns: List<DUNEntity> = duns?.map {dun -> DUNEntity(
            ean= dun.ean,
            product= entity,
            quantity= dun.quantity)
        } ?: emptyList()

        entity.duns = duns.toMutableSet()

        return entity
    }
}
