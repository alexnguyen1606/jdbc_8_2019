package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.modelmapper.ModelMapper;

public class CustomerConverter {
    private ModelMapper modelMapper;
    public CustomerConverter() {
        modelMapper = new ModelMapper();
    }

    public CustomerDTO convertToDTO(CustomerEntity entity){

        return modelMapper.map(entity,CustomerDTO.class);
    }
    public CustomerEntity convertToEntity(CustomerDTO dto){ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, CustomerEntity.class);
    }
}
