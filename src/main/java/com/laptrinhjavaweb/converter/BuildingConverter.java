package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BuildingConverter {
    public BuildingDTO covertToDTO(BuildingEntity buildingEntity){
        ModelMapper modelMapper = new ModelMapper();
        BuildingDTO dto= modelMapper.map(buildingEntity,BuildingDTO.class);
        dto.setBuildingTypes(convertToBuildingTypes(buildingEntity.getType()));
        return dto;
    }
    public BuildingEntity covertToEntity(BuildingDTO buildingDTO){
        ModelMapper modelMapper = new ModelMapper();
        BuildingEntity entity= modelMapper.map(buildingDTO,BuildingEntity.class);
        entity.setType(convertToTypes(buildingDTO.getBuildingTypes()));
        return entity;
    }

    private String[] convertToBuildingTypes(String types){
        if (StringUtils.isNotBlank(types)){
            String[] result = types.trim().split(",");
            return result;
        }
        else {
            return new String[]{};
        }

    }
    private String convertToTypes(String[] buildingTypes){
        StringBuilder result = new StringBuilder("");
        if (buildingTypes.length>0 && buildingTypes!=null){
            for (String type : buildingTypes){
                if (result.length()>0 && StringUtils.isNotBlank(type)){
                    result.append(","+type);
                }else {
                    result.append(type);
                }
            }
        }
        return result.toString();
    }
}
