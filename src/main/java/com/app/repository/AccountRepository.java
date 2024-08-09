package com.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);

    @Query("SELECT a FROM Account a WHERE a.balance > :balance")
    List<Account> findAccountsByBalanceGreaterThan(@Param("balance") Double balance);
    
    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.id = :customerId")
    int updateAccountBalanceByCustomerId(@Param("balance") Double balance, @Param("customerId") Long customerId);
}
