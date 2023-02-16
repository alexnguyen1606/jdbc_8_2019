package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;

public class RentAreaConverter {
    public RentAreaDTO convertToDTO(RentAreaEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity,RentAreaDTO.class);
    }
    public RentAreaEntity convertToEntity(RentAreaDTO rentAreaDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rentAreaDTO,RentAreaEntity.class);
    }
}
