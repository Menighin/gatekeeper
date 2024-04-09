package com.menighin.gatekeeper.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "username")
    private val username: String,

    @Column(name = "password")
    private val password: String,

    @Column(name = "locked")
    val locked: Boolean = false,

    @Column(name = "enabled")
    private val enabled: Boolean = true,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var userRoles: MutableSet<UserRole> = mutableSetOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var userPermissions: MutableSet<UserPermission> = mutableSetOf()

) : AuditableEntity(), UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val rolesGrants = userRoles.map { SimpleGrantedAuthority(it.role.name) }
        val permissionGrants = userPermissions.map { SimpleGrantedAuthority(it.permission.name) }
        return (rolesGrants + permissionGrants).toMutableList()
    }

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = enabled

    override fun isAccountNonLocked() = !locked

    override fun isCredentialsNonExpired() = enabled

    override fun isEnabled() = enabled
}