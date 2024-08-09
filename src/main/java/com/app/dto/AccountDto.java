package com.app.dto;

import com.app.enums.AccountType;
import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private Long customerId;
}
