package com.example.transaction_service.model.transaction.entity;

import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.model.transaction.type.entity.TransactionType;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_sender_id")
    private Account sender;
    @ManyToOne
    @JoinColumn(name = "account_recipient_id")
    private Account recipient;
    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;
    @Column(name = "amount")
    private double amount;
    @Column(name = "time")
    private Timestamp time;
}
