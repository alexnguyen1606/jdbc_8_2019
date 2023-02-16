package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {

	List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable);
	List<BuildingDTO> findAll(Pageable pageable);
	List<BuildingDTO> findAll();
	BuildingDTO save(BuildingDTO buildingDTO);
	BuildingDTO findById(Long id);
	BuildingDTO update(BuildingDTO buildingDTO);
	void delete(Long[] ids);
	Map<String,String> getBuildingTypes();
	Map<String,String> getDistricts ();
	void setType(BuildingDTO buildingDTO);
}