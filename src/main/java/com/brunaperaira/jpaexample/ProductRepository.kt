package com.brunaperaira.jpaexample

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : CrudRepository<ProductEntity, UUID?> {

    @EntityGraph(
        type = EntityGraph.EntityGraphType.LOAD,
        attributePaths = ["duns"]
    )
    override fun findAll(): List<ProductEntity>
}
