package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.ProcessDTO;

import java.util.List;

public interface IProcessService {
    List<ProcessDTO> findByCustomerId(Long customerId);
}
