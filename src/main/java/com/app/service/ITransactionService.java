package com.app.service;

import java.util.List;

import com.app.dto.TransactionDto;
import com.app.pojos.Transaction;

public interface ITransactionService {

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto getTransactionById(Long id);

    TransactionDto updateTransaction(Long id, TransactionDto transactionDto);

    void deleteTransaction(Long id);

    List<Transaction> getTransactionsByAccountId(Long accountId);
}
