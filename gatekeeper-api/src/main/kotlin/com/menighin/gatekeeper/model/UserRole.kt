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
@Table(name = "users_roles")
class UserRole(

    @EmbeddedId
    var userRoleId: UserRoleId

) : AuditableEntity() {

    constructor(userId: Long, roleId: Long): this(UserRoleId(userId, roleId))

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    lateinit var user: User

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    lateinit var role: Role

}


@Embeddable
data class UserRoleId(

    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "role_id")
    var roleId: Long

) : Serializable