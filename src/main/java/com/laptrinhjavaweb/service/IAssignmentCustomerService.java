package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;

import java.util.List;

public interface IAssignmentCustomerService {
    boolean existAssigment(Long customerId,Long staffId);
    void assignCustomer(Long customerId,Long[] staffs);
    AssignmentCustomerDTO save(AssignmentCustomerDTO assignmentCustomerDTO);
    void deleteByCustomerIdAndUserId(Long customerId,Long userId);
    List<AssignmentCustomerDTO> findByCustomerId(Long customerId);
    AssignmentCustomerDTO findByCustomerIdAndUserId(Long customerId,Long userId);
    AssignmentCustomerDTO findOne(Long id);
}
