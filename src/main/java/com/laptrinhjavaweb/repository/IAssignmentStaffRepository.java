package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentStaffEntity;

import java.util.List;
import java.util.Map;

public interface IAssignmentStaffRepository extends JpaRepository<Long, AssignmentStaffEntity> {

   // void deleteById(Long id);
    AssignmentStaffEntity findByBuildingIdAndStaffId(Map<String,Object> properties);
    List<AssignmentStaffEntity> findByBuildingId(Map<String,Object> properties);

    void deleteByBuildingIdAndStaffId(Map<String,Object> properties);
    void deleteByBuildingId(Map<String,Object> properties);

}
