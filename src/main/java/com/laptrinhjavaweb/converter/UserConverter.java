package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;

public class UserConverter {
    private ModelMapper modelMapper;

    public UserConverter() {
        modelMapper = new ModelMapper();
    }
    public UserDTO convertToDTO(UserEntity entity){
        return modelMapper.map(entity,UserDTO.class);
    }
    public UserEntity covertToEntity(UserDTO dto){
        return modelMapper.map(dto,UserEntity.class);
    }
}
