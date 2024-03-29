package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingRepository extends JpaRepository<Long, BuildingEntity>  {
    List<BuildingEntity> findAll(Map<String,Object> params, Pageable pageable,BuildingSearchBuilder builder);
    List<BuildingEntity> findAll(Map<String,Object> params,Pageable pageable);
    Long save(BuildingEntity buildingEntity);

}
