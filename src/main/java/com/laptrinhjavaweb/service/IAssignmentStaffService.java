package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.DTO.AssignmentStaffDTO;
import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.DTO.RentAreaDTO;
import com.laptrinhjavaweb.builder.AssigmentStaffBuilder;

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
}
