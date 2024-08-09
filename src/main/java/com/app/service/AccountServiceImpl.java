package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.AccountDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Account;
import com.app.pojos.Customer;
import com.app.repository.AccountRepository;
import com.app.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = mapper.map(accountDto, Account.class);
        Account savedAccount = accountRepository.save(account);
        return mapper.map(savedAccount, AccountDto.class);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account id: " + id));
        return mapper.map(account, AccountDto.class);
    }

    @Override
    @Transactional
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account id: " + id));
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getBalance());
        Customer customer = customerRepository.findById(accountDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer id: " + accountDto.getCustomerId()));
        account.setCustomer(customer);
        Account updatedAccount = accountRepository.save(account);
        return mapper.map(updatedAccount, AccountDto.class);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account id: " + id));
        accountRepository.delete(account);
    }

    @Override
    public List<AccountDto> getAccountsByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accounts.stream()
                .map(account -> mapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAccountsByBalanceGreaterThan(Double balance) {
        List<Account> accounts = accountRepository.findAccountsByBalanceGreaterThan(balance);
        return accounts.stream()
                .map(account -> mapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateAccountBalanceByCustomerId(Double balance, Long customerId) {
        int updatedCount = accountRepository.updateAccountBalanceByCustomerId(balance, customerId);
        if (updatedCount == 0) {
            throw new RuntimeException("No accounts found for customer ID: " + customerId);
        }
    }
}
