package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.DTO.CustomerDTO;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface ICustomerService {
    public List<CustomerDTO> findAll(Pageable pageable);
    public List<CustomerDTO> findAll();
    public List<CustomerDTO> findAll(CustomerSearchBuilder fieldSearch, Pageable pageable);
}
