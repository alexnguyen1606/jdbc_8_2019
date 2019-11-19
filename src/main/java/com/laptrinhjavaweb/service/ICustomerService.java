package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.DTO.CustomerDTO;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface ICustomerService {
     List<CustomerDTO> findAll(Pageable pageable);
     List<CustomerDTO> findAll();
     List<CustomerDTO> findAll(CustomerSearchBuilder fieldSearch, Pageable pageable);
     CustomerDTO findById(Long id);
     CustomerDTO save(CustomerDTO customerDTO);
     CustomerDTO update(CustomerDTO customerDTO);
     void delete(Long[] ids);
}
