package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.service.ITransactionService;

import java.util.List;

public class TransactionService implements ITransactionService {
    @Override
    public List<TransactionDTO> findByCustomerId(Long customerId) {
        return null;
    }
}
