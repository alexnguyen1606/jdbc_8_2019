package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> findByCustomerId(Long customerId);
}
