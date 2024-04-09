package com.menighin.gatekeeper.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "users_permissions")
class UserPermission(

    @EmbeddedId
    var userPermissionId: UserPermissionId

) : AuditableEntity() {

    constructor(userId: Long, permissionId: Long): this(UserPermissionId(userId, permissionId))

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    lateinit var user: User

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    lateinit var permission: Permission
}


@Embeddable
data class UserPermissionId(

    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "permission_id")
    var permissionId: Long

) : Serializable