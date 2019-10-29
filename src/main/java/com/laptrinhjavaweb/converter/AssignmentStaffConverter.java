package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.AssignmentStaffDTO;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import org.modelmapper.ModelMapper;

public class AssignmentStaffConverter {
    public AssignmentStaffDTO convertToDTO(AssignmentStaffEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        AssignmentStaffDTO dto = modelMapper.map(entity,AssignmentStaffDTO.class);
        return dto;
    }
    public AssignmentStaffEntity convertToEntity(AssignmentStaffDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        AssignmentStaffEntity entity = modelMapper.map(dto,AssignmentStaffEntity.class);
        return entity;
    }
}
