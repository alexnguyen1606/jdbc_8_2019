package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentCustomerEntity;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;

import java.util.Map;

public interface IAssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity>{
    AssignmentCustomerEntity findByCustomerIdAndUserId(Map<String,Object> properties);
}
