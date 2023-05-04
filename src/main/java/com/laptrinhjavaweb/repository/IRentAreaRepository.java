package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;

import javax.annotation.ManagedBean;
import java.util.List;
import java.util.Map;
@ManagedBean
public interface IRentAreaRepository extends JpaRepository<Long, RentAreaEntity> {
    void deleteByBuildingId(Map<String,Object> properties);
    List<RentAreaEntity> findByBuildingIdAndValue(Map<String,Object> properties);
}
