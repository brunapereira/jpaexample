package com.brunaperaira.jpaexample

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping
    fun findAll(): ProductsPayload {
        val products = productRepository.findAll()
        return ProductsPayload.fromEntities(products)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ProductPayload {
        val product = productRepository.findByIdOrNull(id) ?: throw Error("Product not found $id")
        return ProductPayload.fromEntity(product)
    }

    @PostMapping
    fun createProduct(@RequestBody productPayload: ProductPayload): ProductPayload {
        val entity = productPayload.toEntity()
        val product = productRepository.save(entity)
        return ProductPayload.fromEntity(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: UUID,
        @RequestBody productPayload: ProductPayload
    ): ProductPayload {
        val product = productRepository.findByIdOrNull(id) ?: throw Error("")

        product.update(productPayload.toEntity())

        val updatedProduct = productRepository.save(product)

        return ProductPayload.fromEntity(updatedProduct)
    }
}
