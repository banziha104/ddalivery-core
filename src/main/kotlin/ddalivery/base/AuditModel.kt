package com.lyj.ddalivery.ddalivery.base

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
open abstract class AuditModel<PK : Serializable?> : BaseEntity<PK>() {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private val createdDt: LocalDateTime? = null
    @LastModifiedDate
    @Column(updatable = false)
    private val updatedDt: LocalDateTime? = null
}
