package com.cilek.cilekbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "cilek_user")
@Table(name = "cilek_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class _User extends Audit {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // TODO: There can be is_active field to check if the user is active or not.

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

}
