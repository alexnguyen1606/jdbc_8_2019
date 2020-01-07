package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IAssignmentStaffRepository;

import java.util.List;
import java.util.Map;

public class AssignmentStaffRepository extends SimpleJpaRepository<AssignmentStaffEntity>
        implements IAssignmentStaffRepository {

    @Override
    public AssignmentStaffEntity findByBuildingIdAndStaffId(Map<String,Object> properties) {
        List<AssignmentStaffEntity> assignmentStaff = this.finAll(properties);
        return assignmentStaff.size()>0 ? assignmentStaff.get(0) : new AssignmentStaffEntity();
    }

    @Override
    public List<AssignmentStaffEntity> findByBuildingId(Map<String, Object> properties) {
        return this.finAll(properties);
    }



    @Override
    public void deleteByBuildingIdAndStaffId(Map<String, Object> properties) {
        this.deleteSpecial(properties);
    }

    @Override
    public void deleteByBuildingId(Map<String, Object> properties) {
        this.deleteSpecial(properties);
    }


}
