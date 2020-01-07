package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.AssignmentCustomerDTO;
import com.laptrinhjavaweb.entity.AssignmentCustomerEntity;
import org.modelmapper.ModelMapper;

public class AssignmentCustomerConverter {
    private ModelMapper modelMapper;
    public AssignmentCustomerConverter() {
        modelMapper = new ModelMapper();
    }
    public AssignmentCustomerEntity convertToEntity(AssignmentCustomerDTO dto){
        return modelMapper.map(dto,AssignmentCustomerEntity.class);
    }
    public AssignmentCustomerDTO convertToDTO(AssignmentCustomerEntity entity){
        return modelMapper.map(entity,AssignmentCustomerDTO.class);
    }
}
