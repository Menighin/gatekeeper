package com.menighin.gatekeeper.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "permissions")
data class Permission (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    val name: String,

    @OneToMany(mappedBy = "permission")
    var userPermissions: MutableList<UserPermission> = mutableListOf()

) : AuditableEntity()