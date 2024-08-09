package com.app.service;

import java.util.List;

import com.app.dto.AccountDto;
import com.app.pojos.Account;

public interface IAccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto updateAccount(Long id, AccountDto accountDto);

    void deleteAccount(Long id);

    List<AccountDto> getAccountsByCustomerId(Long customerId);
    List<AccountDto> getAccountsByBalanceGreaterThan(Double balance);
    void updateAccountBalanceByCustomerId(Double balance, Long customerId);
}
