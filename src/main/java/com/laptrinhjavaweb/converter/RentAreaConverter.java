package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;

public class RentAreaConverter {
    public RentAreaDTO convertToDTO(RentAreaEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        RentAreaDTO dto = modelMapper.map(entity,RentAreaDTO.class);
        return dto;
    }
    public RentAreaEntity convertToEntity(RentAreaDTO rentAreaDTO){
        ModelMapper modelMapper = new ModelMapper();
        RentAreaEntity entity = modelMapper.map(rentAreaDTO,RentAreaEntity.class);
        return entity;
    }
}
