package com.menighin.gatekeeper.model

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@MappedSuperclass
abstract class AuditableEntity (

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: Instant = Instant.now(),

    @CreatedBy
    @Column(name = "created_by")
    var createdBy: String? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: Instant? = null,

    @LastModifiedBy
    @Column(name = "updated_by")
    var updatedBy: String? = null

)