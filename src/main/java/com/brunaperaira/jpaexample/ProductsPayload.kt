package com.brunaperaira.jpaexample

data class ProductsPayload(
    val products: List<ProductPayload>
) {
    companion object {
        fun fromEntities(entities: List<ProductEntity>): ProductsPayload {
            return ProductsPayload(entities.map { entity ->
                ProductPayload.fromEntity(entity)
            })
        }
    }
}
