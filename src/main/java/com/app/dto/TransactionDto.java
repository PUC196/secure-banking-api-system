package com.app.dto;

import lombok.Data;

@Data
public class TransactionDto {

    private Long id;
    private Double amount;
    private String transactionType;
    private Long accountId;

    // Default Constructor
    public TransactionDto() {
        super();
    }

    // Parameterized Constructor
    public TransactionDto(Long id, Double amount, String transactionType, Long accountId) {
        super();
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
        this.accountId = accountId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
