package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentStaffDTO;

import java.util.List;

public interface IAssignmentStaffService {
    AssignmentStaffDTO save(AssignmentStaffDTO assignmentStaffDTO);
    AssignmentStaffDTO findOne(Long id);
    AssignmentStaffDTO update(AssignmentStaffDTO assignmentStaffDTO);
    void delete(Long[] ids);
    void deleteOne(Long id);
    List<AssignmentStaffDTO> findByBuildingId(Long buildingId);
    void deleteByBuildingIdAndStaffId(Long buildingId,Long staffId);
    void deleteByBuildingId(Long buildingId);
    List<AssignmentStaffDTO> findAll();
    void assignBuilding(Long buildingId, Long[] staffs);
    boolean existAssignment(Long buildingId,Long staffId);
    AssignmentStaffDTO findByBuildingIdAndStaffId(Long buildingId,Long staffId);
}
