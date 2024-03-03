package com.cilek.cilekbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", updatable = false, nullable = false)
    private UUID account_id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String number;

    @Column(name = "account_name", nullable = false, unique = true)
    private String name;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private _User user;
}
