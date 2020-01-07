package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.DTO.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> findByCustomerId(Long customerId);
}
