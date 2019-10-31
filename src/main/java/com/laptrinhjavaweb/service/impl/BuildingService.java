package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.DTO.RentAreaDTO;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;

import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;
import org.apache.commons.lang3.StringUtils;


public class BuildingService implements IBuildingService{
	private BuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;
	private RentAreaService rentAreaService;
	public BuildingService() {
	buildingRepository = new BuildingRepository();
	buildingConverter = new BuildingConverter();
	rentAreaService = new RentAreaService();
	}
	public List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable){

		Map<String, Object> properties = convertToMapProperties(fieldSearch);

		return buildingRepository.findAll(properties,pageable,fieldSearch)
				.stream().map(item -> buildingConverter.covertToDTO(item)).collect(Collectors.toList());
	}




    @Override
	public List<BuildingDTO> findAll(Pageable pageable) {

		return buildingRepository.findAll(pageable)
				.stream().map(item-> buildingConverter.covertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public List<BuildingDTO> findAll() {
		return buildingRepository.findAll().stream()
				.map(item -> buildingConverter.covertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
	    if (buildingDTO.getId()==null) {
            BuildingEntity entity = buildingConverter.covertToEntity(buildingDTO);
            entity.setCreatedDate(new Date());
            entity.setModifiedDate(new Date());
            entity.setCreatedBy("abc");
            Long id = buildingRepository.save(entity);
			saveRentArea(id,buildingDTO.getAreaRent());
            return findOne(id);
        }
		else {
		    return new BuildingDTO();
        }
	}

	@Override
	public BuildingDTO findOne(Long id) {
		if (id == null || buildingRepository.findById(id)== null){
			return new BuildingDTO();
		}
		else
		{
		return buildingConverter.covertToDTO(buildingRepository.findById(id));
		}
	}

	@Override
	public BuildingDTO update(BuildingDTO buildingDTO) {
		if (buildingDTO.getId()==null){
			return new BuildingDTO();
		}
		else {

		BuildingEntity buildingEntity = buildingConverter.covertToEntity(buildingDTO);
		BuildingEntity buildingEntityInDB = buildingRepository.findById(buildingDTO.getId());

		buildingEntity.setCreatedDate(buildingEntityInDB.getCreatedDate());
		buildingEntity.setCreatedBy(buildingEntityInDB.getCreatedBy());
		buildingEntity.setModifiedDate(new Date());
		buildingEntity.setModifiedBy("abc");

		buildingRepository.update(buildingEntity);
		return findOne(buildingDTO.getId());
		}

	}

	@Override
	public List<BuildingDTO> delete(Long[] ids) {
		for (Long id : ids){
			buildingRepository.deleteById(id);
		}
		return findAll();
	}

	private Map<String,Object> convertToMapProperties(BuildingSearchBuilder fieldSearch) {
		Map<String, Object> properties = new HashMap<>();
			try {
                Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
				for (Field field:fields) {
					if ( !field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
                            && !field.getName().startsWith("rentArea") && !field.getName().equals("staffId")) {
						field.setAccessible(true);
						if (field.getName().equals("buildingArea") || field.getName().equals("numberOfBasement")) {
							if (field.get(fieldSearch) != null && StringUtils.isNotEmpty((String) field.get(fieldSearch))) {
								properties.put(field.getName().toLowerCase(), Integer.parseInt((String) field.get(fieldSearch)));
							}
						} else {
							properties.put(field.getName(), field.get(fieldSearch));
						}
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		return properties;
	}
    private String convertBuildingTypes(String[] buildingTypes){
	    StringBuilder result = new StringBuilder("");
        if (buildingTypes.length>0 && buildingTypes!=null){
            for (String type : buildingTypes){
                if (result.length()>0){
                    result.append(","+type);
                }else {
                    result.append(type);
                }
            }
        }
	    return result.toString();
    }
	private void saveRentArea(Long buildingId,String areaRent){
		String[] listAreaRent = areaRent.trim().split(",");
		for (String value : listAreaRent){
			Integer a = null;
			try{
				a = Integer.parseInt(value);
				RentAreaDTO rentAreaDTO = new RentAreaDTO();
				rentAreaDTO.setValue(a);
				rentAreaDTO.setBuildingId(buildingId);
				rentAreaService.save(rentAreaDTO);
			}catch (Exception e){
				System.out.println(e.toString());
			}

		}
	}
}
