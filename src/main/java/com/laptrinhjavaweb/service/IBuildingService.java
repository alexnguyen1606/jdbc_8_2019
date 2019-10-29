package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {

	List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable);
	List<BuildingDTO> findAll(Pageable pageable);
	List<BuildingDTO> findAll();
	BuildingDTO save(BuildingDTO buildingDTO);
	BuildingDTO findOne(Long id);
	BuildingDTO update(BuildingDTO buildingDTO);
	List<BuildingDTO> delete(Long[] ids);
}