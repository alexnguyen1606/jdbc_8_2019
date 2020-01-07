package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.AssignmentStaffDTO;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import org.modelmapper.ModelMapper;

public class AssignmentStaffConverter {
    private ModelMapper modelMapper;
    public AssignmentStaffConverter() {
        modelMapper = new ModelMapper();
    }

    public AssignmentStaffDTO convertToDTO(AssignmentStaffEntity entity){

        AssignmentStaffDTO dto = modelMapper.map(entity,AssignmentStaffDTO.class);
        return dto;
    }
    public AssignmentStaffEntity convertToEntity(AssignmentStaffDTO dto){

        AssignmentStaffEntity entity = modelMapper.map(dto,AssignmentStaffEntity.class);
        return entity;
    }
}
