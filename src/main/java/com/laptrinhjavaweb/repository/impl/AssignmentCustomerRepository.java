package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.AssignmentCustomerEntity;
import com.laptrinhjavaweb.repository.IAssignmentCustomerRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;

import java.util.List;
import java.util.Map;

public class AssignmentCustomerRepository extends SimpleJpaRepository<AssignmentCustomerEntity>
        implements IAssignmentCustomerRepository  {

    @Override
    public AssignmentCustomerEntity findByCustomerIdAndUserId(Map<String, Object> properties) {
        List<AssignmentCustomerEntity> assignmentCustomer = this.finAll(properties);
        return assignmentCustomer.size()>0 ? assignmentCustomer.get(0) : new AssignmentCustomerEntity();
    }
}
