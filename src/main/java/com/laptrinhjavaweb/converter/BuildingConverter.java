package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.RentAreaService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BuildingConverter {
    private IRentAreaService areaService;
    public BuildingConverter(){
        areaService = new RentAreaService();
    }
    public BuildingDTO covertToDTO(BuildingEntity buildingEntity){
        ModelMapper modelMapper = new ModelMapper();
        BuildingDTO dto= modelMapper.map(buildingEntity,BuildingDTO.class);
        dto.setBuildingTypes(convertToBuildingTypes(buildingEntity.getType()));
        dto.setAreaRent(areaService.getAllAreaRentByBuildingId(dto.getId()));
        dto.setAddress(getAddress(dto));
        return dto;
    }
    public BuildingEntity covertToEntity(BuildingDTO buildingDTO){
        ModelMapper modelMapper = new ModelMapper();
        BuildingEntity entity= modelMapper.map(buildingDTO,BuildingEntity.class);
        entity.setType(convertToTypes(buildingDTO.getBuildingTypes()));
        return entity;
    }
    private String getAddress(BuildingDTO dto){
        StringBuilder address = new StringBuilder(dto.getStreet()+" ,"+dto.getWard());
        String districtName = "";
        for(DistrictsEnum item : DistrictsEnum.values()){
            if (item.toString().equals(dto.getDistrict())){
                districtName = item.getValue();
                break;
            }
        }
        address.append(" ,"+districtName);
        return address.toString();
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
            result.append(String.join(",",buildingTypes));
        }
        return result.toString();
    }
}
