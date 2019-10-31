package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentStaffEntity;

import java.util.List;
import java.util.Map;

public interface IAssignmentStaffRepository extends JpaRepository<AssignmentStaffEntity> {

   // void deleteById(Long id);
    AssignmentStaffEntity findByStaffIdAndBuildingId(Map<String,Object> properties);
}
