package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.DTO.AssignmentStaffDTO;
import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.DTO.RentAreaDTO;

import java.util.List;

public interface IAssignmentStaffService {
    AssignmentStaffDTO save(AssignmentStaffDTO assignmentStaffDTO);
    AssignmentStaffDTO findOne(Long id);
    AssignmentStaffDTO update(AssignmentStaffDTO assignmentStaffDTO);
    List<AssignmentStaffDTO> delete(Long[] ids);
    List<AssignmentStaffDTO> deleteOne(Long id);
}
