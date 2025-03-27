package com.mega.service.account.module.account.entity;

import com.mega.service.account.module.users.entity.User;
import com.mega.service.account.utils.entity.Auditing;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
