package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IRentAreaService;
import com.laptrinhjavaweb.service.impl.RentAreaService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

public class BuildingConverter {
    private IRentAreaService areaService;
    private ModelMapper modelMapper;
    public BuildingConverter(){
        modelMapper = new ModelMapper();
        areaService = new RentAreaService();
    }
    public BuildingDTO covertToDTO(BuildingEntity buildingEntity){

        BuildingDTO dto= modelMapper.map(buildingEntity,BuildingDTO.class);
        dto.setBuildingTypes(convertToBuildingTypes(buildingEntity.getType()));
        dto.setAreaRent(areaService.getAllAreaRentByBuildingId(dto.getId()));
        dto.setAddress(getAddress(dto));
        dto.setTypeInView(getType(dto.getType()));
        return dto;
    }
    public BuildingEntity covertToEntity(BuildingDTO buildingDTO){

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
    private String getType(String types){
        StringBuilder type = new StringBuilder("");
        for (BuildingTypesEnum enums : BuildingTypesEnum.values()){
            if (types.contains(enums.toString())){
                if (type.length()>0){
                    type.append(","+enums.getValue());
                }
                else {
                    type.append(enums.getValue());
                }

            }
        }
        return type.toString();
    }
}
