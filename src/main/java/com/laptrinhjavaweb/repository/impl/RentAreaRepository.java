package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

import java.util.Map;

public class RentAreaRepository extends SimpleJpaRepository<RentAreaEntity> implements IRentAreaRepository {
    @Override
    public void deleteByBuildingId(Map<String, Object> properties) {
        this.deleteSpecial(properties);
    }
}
