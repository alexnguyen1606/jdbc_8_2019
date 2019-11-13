package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.DTO.RentAreaDTO;
import com.laptrinhjavaweb.builder.AssigmentStaffBuilder;
import com.laptrinhjavaweb.builder.RentAreaBuilder;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class RentAreaService implements IRentAreaService {
    private IRentAreaRepository rentAreaRepository;
    private RentAreaConverter converter;
    public RentAreaService() {
        rentAreaRepository = new RentAreaRepository();
        converter = new RentAreaConverter();
    }

    @Override
    public RentAreaDTO save(RentAreaDTO rentAreaDTO) {
        if (rentAreaDTO.getId()==null){
            RentAreaEntity entity = converter.convertToEntity(rentAreaDTO);
            entity.setCreatedDate(new Date());
            entity.setModifiedDate(new Date());
            entity.setCreatedBy("abc");
            Long id = rentAreaRepository.insert(entity);
            return findOne(id);
        }
        return new RentAreaDTO();
    }

    @Override
    public RentAreaDTO findOne(Long id) {
        if (id == null || rentAreaRepository.findById(id) == null) {
            return new RentAreaDTO();
        } else {
            return converter.convertToDTO(rentAreaRepository.findById(id));
        }
    }

    @Override
    public void deleteByBuildingId(Long buildingId) {
        RentAreaBuilder builder = new RentAreaBuilder.Builder()
                .setBuildingId(buildingId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        rentAreaRepository.deleteByBuildingId(properties);
    }

    @Override
    public void deleteOne(Long id) {
        if (id!=null){
            rentAreaRepository.deleteById(id);
        }
    }

    @Override
    public List<RentAreaDTO> findByBuildingId(Long buildingId) {
        RentAreaBuilder builder = new RentAreaBuilder.Builder()
                .setBuildingId(buildingId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        return rentAreaRepository.finAll(properties).stream()
                .map(item -> converter.convertToDTO(item)).collect(Collectors.toList());
    }
    @Override
    public void saveAll(Long buildingId,String areaRent){
        String[] listAreaRent = areaRent.trim().split(",");
        for (String value : listAreaRent){
            Integer a = null;
            try{
                a = Integer.parseInt(value);
                RentAreaDTO rentAreaDTO = new RentAreaDTO();
                rentAreaDTO.setValue(a);
                rentAreaDTO.setBuildingId(buildingId);
                save(rentAreaDTO);
            }catch (Exception e){
                System.out.println(e.toString());
            }

        }
    }

    @Override
    public void updateAll(Long buildingId, String areaRent) {
        List<RentAreaDTO> rentAreas = findByBuildingId(buildingId);
        String[] areaRentValues = areaRent.trim().split(",");
        List<Integer> listAreaRent = new ArrayList<>();
        for (String value : areaRentValues){
            try {
                listAreaRent.add(Integer.parseInt(value));
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        for (RentAreaDTO rentAreaDTO : rentAreas){
            if (!listAreaRent.contains(rentAreaDTO.getValue())){
                deleteOne(rentAreaDTO.getId());
            }
        }
    }

    @Override
    public String getAllAreaRentByBuildingId(Long buildingId){
        List<RentAreaDTO> listRentArea = findByBuildingId(buildingId);
        StringBuilder rentArea = new StringBuilder("");
        for (RentAreaDTO rentAreaDTO : listRentArea){
            if (rentArea.length()>0){
                rentArea.append(","+rentAreaDTO.getValue());
            }else {
                rentArea.append(rentAreaDTO.getValue());
            }
        }
        return rentArea.toString();
    }
    private Map<String,Object> convertToMapProperties(RentAreaBuilder rentAreaBuilder){
        Map<String,Object> properties = new HashMap<>();
        Field[] fields = rentAreaBuilder.getClass().getDeclaredFields();
        try {
        for (Field field : fields){
            if (field.getName().equals("buildingId")){
                field.setAccessible(true);

                    properties.put(field.getName().toLowerCase(),field.get(rentAreaBuilder));
                }
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
