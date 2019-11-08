package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.Map;

public interface IRentAreaRepository extends JpaRepository<RentAreaEntity> {
    void deleteByBuildingId(Map<String,Object> properties);
}
