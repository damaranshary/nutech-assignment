package com.nutech.nutechassignment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("t_transaction")
public class Transaction {
    @Id
    @Column("invoice_number")
    private String invoiceNumber;

    @Column("type")
    private String transactionType;

    private String description;

    @Column("total_amount")
    private String totalAmount;

    @Column("created_on")
    private LocalDateTime createdOn;

    @Column("user_id")
    private String userId;
}
