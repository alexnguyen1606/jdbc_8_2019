package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.List;
import java.util.Map;

public interface IRentAreaRepository extends JpaRepository<RentAreaEntity> {
    void deleteByBuildingId(Map<String,Object> properties);
    List<RentAreaEntity> findByBuildingIdAndValue(Map<String,Object> properties);
}
