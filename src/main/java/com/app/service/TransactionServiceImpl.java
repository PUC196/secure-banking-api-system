package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.TransactionDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Transaction;
import com.app.repository.TransactionRepository;
import com.app.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = mapper.map(transactionDto, Transaction.class);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapper.map(savedTransaction, TransactionDto.class);
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction id"+id));
        return mapper.map(transaction, TransactionDto.class);
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction id"+id));
        mapper.map(transactionDto, transaction);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return mapper.map(updatedTransaction, TransactionDto.class);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction id"+id));
        transactionRepository.delete(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
