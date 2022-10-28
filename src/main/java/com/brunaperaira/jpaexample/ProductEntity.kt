package com.brunaperaira.jpaexample

import java.util.*
import javax.persistence.*

@Table(name = "product")
@Entity(name = "Product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable=false)
    var id: UUID? = null,

    var name: String,

    @OneToMany(
        mappedBy= "product",
        fetch=FetchType.EAGER,
        cascade = [CascadeType.ALL],
    )
    var duns: MutableSet<DUNEntity>
) {
    fun update(newEntity: ProductEntity) {
        val dunsToSave = newEntity.duns.map { it.product = this; it }.toMutableSet()
        name = newEntity.name
        duns =  dunsToSave
    }
}
